package com.zhouzhixiang.redis.redis.chapter4;

/**
 * @program: 秒杀测试线程类
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-05-10 20:33
 */
public class SecondKillHandler implements Runnable {
    private RedisUtil redisUtil;
    private SecondKillService secondKillService;
    private String lockey;

    public SecondKillHandler(RedisUtil redisUtil, SecondKillService secondKillService, String lockey) {
        this.redisUtil = redisUtil;
        this.secondKillService = secondKillService;
        this.lockey = lockey;
    }

    @Override
    public void run() {
        secondKillService.secondKill(redisUtil, lockey);
    }
}
