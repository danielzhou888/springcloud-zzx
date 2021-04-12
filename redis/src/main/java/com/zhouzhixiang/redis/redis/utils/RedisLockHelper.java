package com.zhouzhixiang.redis.redis.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * redis实现分布式锁工具Bean
 * @author zhouzhixiang
 * */
@Component
public class RedisLockHelper {
 
    private static final Logger log = LoggerFactory.getLogger(RedisLockHelper.class);
 
    /**
     * 默认轮休获取锁间隔时间， 单位：毫秒
     */
    private static final int DEFAULT_ACQUIRE_RESOLUTION_MILLIS = 100;
 
    private static final String UNLOCK_LUA;

    private static final String LOCK_REDIS_PREFIX = "cc:";
 
    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }
 
    private RedisTemplate redisTemplate;
 
    private final ThreadLocal<Map<String, LockVO>> lockMap = new ThreadLocal<>();
 
    public RedisLockHelper(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
 
    /**
     * 获取锁，没有获取到则一直等待
     *
     * @param key    redis key
     * @param expire 锁过期时间, 单位 秒
     */
    public void lock(final String key, long expire) {
        try {
            acquireLock(key, expire, -1);
        } catch (Exception e) {
            throw new RuntimeException("acquire lock exception", e);
        }
    }
 
    /**
     * 获取锁，指定时间内没有获取到，返回false。否则 返回true
     *
     * @param key            redis key
     * @param expire         锁过期时间, 单位 秒
     * @param acquireTimeout 获取锁超时时间, -1代表永不超时, 单位 秒
     */
    public boolean lock(final String key, long expire, long acquireTimeout) throws RuntimeException {
        try {
            return acquireLock(key, expire, acquireTimeout);
        } catch (Exception e) {
            throw new RuntimeException("acquire lock exception", e);
        }
    }

    /**
     * 获取锁，指定时间内没有获取到，返回false。否则 返回true
     *
     * @param key            redis key
     * @param expire         锁过期时间, 单位 秒
     * @param acquireTimeout 获取锁超时时间, -1代表永不超时, 单位 秒
     * @param value          客户端唯一标识：防止误释放锁
     */
    public boolean lock(final String key, long expire, long acquireTimeout, String value) throws RuntimeException {
        try {
            return acquireLock(key, expire, acquireTimeout);
        } catch (Exception e) {
            throw new RuntimeException("acquire lock exception", e);
        }
    }
 
    /**
     * 释放锁
     *
     * @param key redis key
     */
    public void unlock(String key) {
        try {
            release(key);
        } catch (Exception e) {
            throw new RuntimeException("release lock exception", e);
        }
    }


    /**
     *
     * @param key
     * @param expire  超时时间-秒
     * @param acquireTimeout  获取锁超时时间-秒
     * @return
     * @throws InterruptedException
     */
    private boolean acquireLock(String key, long expire, long acquireTimeout) throws InterruptedException {
        //如果之前获取到了并且没有超时，则返回获取成功
        boolean acquired = acquired(key);
        if (acquired) {
            return true;
        }
 
        long acquireTime = acquireTimeout == -1 ? -1 : acquireTimeout * 1000 + System.currentTimeMillis();
        String currentTimeStr = DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss.SSS");
        String acquireTimeStr = DateFormatUtils.format(acquireTime,"yyyy-MM-dd HH:mm:ss.SSS");
        log.info("当前时间：{}，超时时间：{}",currentTimeStr,acquireTimeStr);
 
 
        //同一个进程，对于同一个key锁，只允许先到的去尝试获取。
        synchronized (key.intern()) {
            String lockId = UUID.randomUUID().toString();
            do {
                long before = System.currentTimeMillis();
 
                boolean hasLock = tryLock(key, expire, lockId);
 
                //获取锁成功
                if (hasLock) {
                    long after = System.currentTimeMillis();
                    Map<String, LockVO> map = lockMap.get();
                    if (map == null) {
                        map = new HashMap<>(2);
                        lockMap.set(map);
                    }
                    map.put(key, new LockVO(1, lockId, expire * 1000 + before, expire * 1000 + after));
                    log.debug("acquire lock {} {} ", key, 1);
                    return true;
                }
 
                Thread.sleep(DEFAULT_ACQUIRE_RESOLUTION_MILLIS);
 
            } while (acquireTime == -1 || acquireTime > System.currentTimeMillis());
        }
        log.debug("acquire lock {} fail，because timeout ", key);
        return false;
    }
 
    private boolean acquired(String key) {
        Map<String, LockVO> map = lockMap.get();
        if (map == null || map.size() == 0 || !map.containsKey(key)) {
            return false;
        }
 
        LockVO vo = map.get(key);
 
        if (vo.beforeExpireTime < System.currentTimeMillis()) {
            log.debug("lock {} maybe release, because timeout ", key);
            return false;
        }
        int after = ++vo.count;
        log.debug("acquire lock {} {} ", key, after);
        return true;
    }
 
    private void release(String key) {
        Map<String, LockVO> map = lockMap.get();
        if (map == null || map.size() == 0 || !map.containsKey(key)) {
            return;
        }
 
        LockVO vo = map.get(key);
 
        if (vo.afterExpireTime < System.currentTimeMillis()) {
            log.debug("release lock {}, because timeout ", key);
            map.remove(key);
            return;
        }
        int after = --vo.count;
        log.debug("release lock {} {} ", key, after);
 
        if (after > 0) {
            return;
        }
 
        map.remove(key);
        RedisCallback<Boolean> callback = (connection) ->
                connection.eval(UNLOCK_LUA.getBytes(StandardCharsets.UTF_8), ReturnType.BOOLEAN, 1,
                        (LOCK_REDIS_PREFIX + key).getBytes(StandardCharsets.UTF_8), vo.value.getBytes(StandardCharsets.UTF_8));
        redisTemplate.execute(callback);
    }
 
 
    private boolean tryLock(String key, long expire, String lockId) {
        RedisCallback<Boolean> callback = (connection) ->
                connection.set((LOCK_REDIS_PREFIX + key).getBytes(StandardCharsets.UTF_8),
                        lockId.getBytes(StandardCharsets.UTF_8), Expiration.seconds(expire), RedisStringCommands.SetOption.SET_IF_ABSENT);
        return (Boolean) redisTemplate.execute(callback);
    }
 
    private static class LockVO {
        private int count;
        private String value;
        private long beforeExpireTime;
        private long afterExpireTime;
 
        LockVO(int count, String value, long beforeExpireTime, long afterExpireTime) {
            this.count = count;
            this.value = value;
            this.beforeExpireTime = beforeExpireTime;
            this.afterExpireTime = afterExpireTime;
        }
    }
 
}