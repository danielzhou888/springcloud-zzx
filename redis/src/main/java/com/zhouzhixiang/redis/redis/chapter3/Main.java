//package com.zhouzhixiang.redis.redis.chapter3;
//
//
//import com.zhouzhixiang.redis.redis.ZhouzhixiangRedisApplication;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.stream.IntStream;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = ZhouzhixiangRedisApplication.class)
//@WebAppConfiguration
//public class Main {
//
//    private static final Logger logger = LoggerFactory.getLogger(Main.class);
//
//    @Autowired
//    private SecondKillService secondKillService;
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    @Test
//    public void test() throws InterruptedException {
//        logger.info("开始");
//        CountDownLatch countDownLatch =  new CountDownLatch(100);
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//        IntStream.rangeClosed(1, 100).forEach(i -> {
//            executorService.execute(new SecondKillHandler(secondKillService, redisUtil, "MSKEY"));
//            countDownLatch.countDown();
//        });
//        countDownLatch.await();
//
////        executorService.shutdown();
//
//    }
//
//    @Test
//    public void testRedis() {
//        redisUtil.set("k1", "v1");
//        String k1 = redisUtil.get("k1");
//        System.out.println(k1);
//    }
//}
