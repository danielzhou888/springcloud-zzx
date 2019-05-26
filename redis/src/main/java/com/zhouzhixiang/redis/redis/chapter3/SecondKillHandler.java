//package com.zhouzhixiang.redis.redis.chapter3;
//
//public class SecondKillHandler implements Runnable {
//    private SecondKillService secondKillService;
//    private RedisUtil redisUtil;
//    private String key;
//
//    public SecondKillHandler(SecondKillService secondKillService, RedisUtil redisUtil, String key) {
//        this.secondKillService = secondKillService;
//        this.redisUtil = redisUtil;
//        this.key = key;
//    }
//
//    @Override
//    public void run() {
//        secondKillService.seckill(redisUtil, key);
//    }
//}
