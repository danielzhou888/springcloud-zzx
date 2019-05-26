//package com.zhouzhixiang.redis.redis.chapter2;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @description: 分布式锁
// * @author zhouzhixiang
// */
//public class RedisLock {
//    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);
//
//    private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;
//
//    private RedisUtil redisUtil;
//
//    /**
//     * Lock key path
//     */
//    private String lockKey;
//
//    /**
//     * 锁超时时间，防止线程在入锁以后，无限期的等待
//     */
//    private int expireMsecs = 60 * 1000;
//
//    /**
//     * 锁等待时间，防止线程饥饿
//     */
//    private int timeoutMsecs = 10 * 1000;
//
//    private volatile boolean locked = false;
//
//    /**
//     * Detailed constructor with default acquire timeout 10000 msecs and lock
//     * expiration of 60000 msecs.
//     *
//     * @param lockKey
//     *            lock key (ex. account:1, ...)
//     */
//    public RedisLock(RedisUtil redisUtil, String lockKey) {
//        this.redisUtil = redisUtil;
//        this.lockKey = lockKey;
//    }
//
//    /**
//     * Detailed constructor with default lock expiration of 60000 msecs.
//     *
//     */
//    public RedisLock(RedisUtil redisUtil, String lockKey, int timeoutMsecs) {
//        this(redisUtil, lockKey);
//        this.timeoutMsecs = timeoutMsecs;
//    }
//
//    /**
//     * Detailed constructor.
//     *
//     */
//    public RedisLock(RedisUtil redisUtil, String lockKey, int timeoutMsecs, int expireMsecs) {
//        this(redisUtil, lockKey, timeoutMsecs);
//        this.expireMsecs = expireMsecs;
//    }
//
//    /**
//     * @return lock key
//     */
//    public String getLockKey() {
//        return lockKey;
//    }
//
//    public String get(final String key) {
//        return redisUtil.get(key);
//    }
//
//    public void set(final String key, final String value) {
//        redisUtil.set(key, value);
//    }
//
//    public boolean setNX(final String key, final String value) {
//        return redisUtil.setIfAbsent(key, value);
//    }
//
//    public String getSet(final String key, final String value) {
//        return redisUtil.getAndSet(key, value);
//    }
//
//    /**
//     * 获得 lock. 实现思路: 主要是使用了redis 的setnx命令,缓存了锁. reids缓存的key是锁的key,所有的共享,
//     * value是锁的到期时间(注意:这里把过期时间放在value了,没有时间上设置其超时时间) 执行过程:
//     * 1.通过setnx尝试设置某个key的值,成功(当前没有这个锁)则返回,成功获得锁
//     * 2.锁已经存在则获取锁的到期时间,和当前时间比较,超时的话,则设置新的值
//     *
//     * @return true if lock is acquired, false acquire timeouted
//     * @throws InterruptedException
//     *             in case of thread interruption
//     */
//    public synchronized boolean lock() throws InterruptedException {
//        int timeout = timeoutMsecs;
//
//        while (timeout >= 0) {
//            long expires = System.currentTimeMillis() + expireMsecs + 1;
//            // 锁到期的时间
//            String expiresStr = String.valueOf(expires);
////            if (this.setNX(lockKey, expiresStr)) {
////                locked = true;
////                return true;
////            }
//
//            String currentValueStr = redisUtil.get(lockKey);
//            if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
//                // 判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
//                // lock is expired
//                // 获取上一个锁到期时间，并设置现在的锁到期时间，
//                // 只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
//                String oldValueStr = this.getSet(lockKey, expiresStr);
//                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
//                    // 防止误删（覆盖，因为key是相同的）了他人的锁——这里达不到效果，这里值会被覆盖，但是因为什么相差了很少的时间，所以可以接受
//                    // [分布式的情况下]:如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
//                    // lock acquired
//                    locked = true;
//                    return true;
//                }
//            }
//
//            timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;
//            /*
//             * 延迟100 毫秒, 这里使用随机时间可能会好一点,可以防止饥饿进程的出现,即,当同时到达多个进程,
//             * 只会有一个进程获得锁,其他的都用同样的频率进行尝试,后面有来了一些进行,也以同样的频率申请锁,这将可能导致前面来的锁得不到满足.
//             * 使用随机的等待时间可以一定程度上保证公平性
//             */
//            Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
//        }
//        return false;
//    }
//
//    /**
//     * Acquired lock release
//     */
//    public synchronized void unlock() {
//        if (locked) {
//            redisUtil.delete(lockKey);
//            locked = false;
//        }
//    }
//}
