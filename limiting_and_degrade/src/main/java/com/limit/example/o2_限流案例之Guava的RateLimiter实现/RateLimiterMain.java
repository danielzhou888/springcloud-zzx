package com.limit.example.o2_限流案例之Guava的RateLimiter实现;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Guava的RateLimiter实现
 * 在Guava中RateLimiter的实现有两种： Bursty和WarmUp
 *
 * bursty是基于token bucket的算法实现，比如
 *
 * RateLimiter rateLimiter=RateLimiter.create(permitPerSecond); //创建一个bursty实例
 *
 * rateLimiter.acquire(); //获取1个permit，当令牌数量不够时会阻塞直到获取为止
 */
@Slf4j
public class RateLimiterMain {

    public static void main(String[] args) throws IOException {
        PayService payService = new PayService();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 20; i++) {
            final int NO = i;
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    Thread.sleep((long) (Math.random() * 1000));
                    payService.doPay("t_"+NO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.countDown();
        System.in.read();
    }

    static class PayService {

        RateLimiter rateLimiter = RateLimiter.create(10); // qps = 10

        public void doPay(String threadName) {
            if (rateLimiter.tryAcquire()) {
                log.info("ThreadName = {}，支付成功", threadName);
            } else {
                log.info("ThreadName = {}，当前支付人数过多，请稍后重试", threadName);
            }
        }


    }
}
