package com.zhouzhixiang.redis.redis.refer_demo.distributed_lock;

import com.zhouzhixiang.redis.redis.utils.RedisLockHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 分布式锁案例1：Redis单实例分布式锁案例
 * 问题：不过，基于单个 Redis 实例实现分布式锁时，会面临实例异常或崩溃的情况，这会导致实例无法提供锁操作，正因为此，Redis 也提供了 Redlock 算法，用来实现基于多个实例的分布式锁
 * @author zhouzhixiang
 * @Date 2021-03-31
 */
public class SingleRedisInstanceMain {

    private final static String LOCK_KEY = "demo_lock_key";

    @Autowired
    private RedisLockHelper redisLockHelper;

    public void lockDemo() {
        // 加锁、设置过期时间
        try {
            redisLockHelper.lock(LOCK_KEY, 2, 2);
            // do some things
        } catch (Exception e) {
            // ....
        } finally {
            // 手动释放锁
            redisLockHelper.unlock(LOCK_KEY);
        }


    }
}
