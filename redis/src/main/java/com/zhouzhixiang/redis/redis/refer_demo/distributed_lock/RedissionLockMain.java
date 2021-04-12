package com.zhouzhixiang.redis.redis.refer_demo.distributed_lock;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * 【Redis-分布式锁】Redlock算法—解决Redis分布式锁的单点问题：https://app.yinxiang.com/fx/cc66d31f-856d-4b8e-b4ca-594735d264c3
 * 红锁：https://github.com/redisson/redisson/wiki/8.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%92%8C%E5%90%8C%E6%AD%A5%E5%99%A8#84-%E7%BA%A2%E9%94%81redlock
 * @author zhouzhixiang
 * @Date 2021-03-31
 */
public class RedissionLockMain {



    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000)
                .addNodeAddress("redis://127.0.0.1:7000").setPassword("1")
                .addNodeAddress("redis://127.0.0.1:7001").setPassword("1")
                .addNodeAddress("redis://127.0.0.1:7002").setPassword("1");
        return Redisson.create(config);
    }

    public void lockDemo() {
        long waitTimeout = 10;
        long leaseTime = 1;

        RedissonClient redissonClient1 = Redisson.create();
        RedissonClient redissonClient2 = Redisson.create();
        RedissonClient redissonClient3 = Redisson.create();

        RLock lock1 = redissonClient1.getLock("lock1");
        RLock lock2 = redissonClient2.getLock("lock2");
        RLock lock3 = redissonClient3.getLock("lock3");

        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);
        try {
            // 同时加锁：lock1, lock2, lock3
            // 红锁在大部分节点上加锁成功就算成功，且设置总超时时间及单个节点超时时间
            redLock.tryLock(waitTimeout, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            redLock.unlock();
        }
    }
}
