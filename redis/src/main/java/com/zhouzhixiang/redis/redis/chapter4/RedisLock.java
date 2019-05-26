package com.zhouzhixiang.redis.redis.chapter4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @program: Redis分布式锁
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-05-10 18:38
 */
public class RedisLock {

    private Logger logger = LoggerFactory.getLogger(RedisLock.class);
    /**
     * Lock timeout, preventing the thread from waiting indefinitely after entering the lock
     */
    private long timeoutMsec = 10 * 1000;
    /**
     * time of lock hold, to prevent thread starved
     */
    private long expireMesc = 60 * 1000;
    private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLS = 100;

    /**
     * lock key path
     */
    private String lockey;

    /**
     * is lockey locked or not
     */
    private volatile boolean locked = false;

    private RedisUtil redisUtil;

    public RedisLock(RedisUtil redisUtil, String lockey) {
        this.redisUtil = redisUtil;
        this.lockey = lockey;
    }

    public RedisLock(RedisUtil redisUtil, String lockey, long timeoutMsec) {
        this.lockey = lockey;
        this.timeoutMsec = timeoutMsec;
        this.redisUtil = redisUtil;
    }

    public RedisLock(RedisUtil redisUtil, String lockey, long timeoutMsec, long expireMesc) {
        this.lockey = lockey;
        this.timeoutMsec = timeoutMsec;
        this.expireMesc = expireMesc;
        this.redisUtil = redisUtil;
    }

    public String getLockey() {
        return lockey;
    }

    public String getLockeyValue() {
        return redisUtil.get(lockey);
    }

    /**
     * set if not exist
     * @param key
     * @param value
     * @return
     */
    private boolean setNx(final String key, final String value) {
        return redisUtil.setIfAbsent(key, value);
    }

    private String getSet(final String key, final String value) {
        return redisUtil.getAndSet(key, value);
    }

    private String get(final String key) {
        return redisUtil.get(key);
    }

    private void set(final String key, final String value) {
        redisUtil.set(key, value);
    }

    /**
     * Thought：</br>
     * 1.Set the value of lockey to redis by setNx() method, return it successfully, and successfully acquire the lock
     * 2.If the lock already exists then get the value of lock to compare with the current time, if time is exceeded, set the new value
     * @return
     */
    public synchronized boolean lock() throws InterruptedException {
        long timeout = timeoutMsec;
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + expireMesc + 1;
            String expiresStr = String.valueOf(expires);
            if (this.setNx(lockey, expiresStr)) {
                locked = true;
                return locked;
            }
            String currentValueStr = redisUtil.get(lockey);
            if (!StringUtils.isEmpty(currentValueStr) && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                // 判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
                // lock is expired
                // 获取上一个锁到期时间，并设置现在的锁到期时间，
                // 只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
                String oldValueStr = this.getSet(lockey, expiresStr);
                if (!StringUtils.isEmpty(oldValueStr) && oldValueStr.equals(expiresStr)) {
                    // 防止误删（覆盖，因为key是相同的）了他人的锁——这里达不到效果，这里值会被覆盖，但是因为什么相差了很少的时间，所以可以接受
                    // [分布式的情况下]:如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
                    // lock acquired
                    locked = true;
                    return locked;
                }
            }
            timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLS;
            /*
             * 延迟100 毫秒, 这里使用随机时间可能会好一点,可以防止饥饿进程的出现,即,当同时到达多个进程,
             * 只会有一个进程获得锁,其他的都用同样的频率进行尝试,后面有来了一些进行,也以同样的频率申请锁,这将可能导致前面来的锁得不到满足.
             * 使用随机的等待时间可以一定程度上保证公平性
             */
            Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLS);
        }
        return false;
    }

    /**
     * Release the lock
     * @return
     */
    public synchronized void unlock() {
        if (locked) {
            redisUtil.delete(lockey);
            locked = false;
        }
    }

}
