package com.zhouzhixiang.redis.redis.chapter4;

import com.zhouzhixiang.redis.redis.ZhouzhixiangRedisApplication;
import com.zhouzhixiang.redis.redis.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 秒杀系统——分布式锁
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-05-10 20:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ZhouzhixiangRedisApplication.class)
@WebAppConfiguration
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(com.zhouzhixiang.redis.redis.chapter4.Main.class);
    @Autowired
    private SecondKillService secondKillService;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void kill() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        IntStream.rangeClosed(1, 100).forEach(i -> {
            executorService.execute(new SecondKillHandler(redisUtil, secondKillService, "MSKEY"));
            countDownLatch.countDown();
        });
        countDownLatch.await();
        executorService.shutdown();

    }

    @Test
    public void init() {
        redisUtil.set("goods:iphoneX", "100");
    }
}
