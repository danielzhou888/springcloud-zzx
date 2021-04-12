package com.zhouzhixiang.redis.redis.refer_demo.lua_command;

import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.nio.charset.StandardCharsets;

/**
 * LUA脚本案例
 * @author zhouzhixiang
 * @Date 2021-03-31
 */
public class LuaDemo {
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

    /**
     * 分布式锁 -》 释放锁：满足原子性，使用lua脚本
     * @param key     锁key
     * @param value   锁value，一般是客户端唯一码，释放时，防止误释放其他线程锁
     */
    public void unLock(String key, String value) {
        RedisCallback<Boolean> callback = (connection) ->
                connection.eval(UNLOCK_LUA.getBytes(StandardCharsets.UTF_8), ReturnType.BOOLEAN, 1,
                        (LOCK_REDIS_PREFIX + key).getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
        redisTemplate.execute(callback);
    }
}
