package com.limit.example.o1_限流案例之Semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 限流算法实战
 *
 * Semaphore比较常见的就是用来做限流操作了。比如下面的场景中，模拟20个客户端请求过来，我们为了减少访问的压力，通过Semaphore来限制请求的流量。
 */
@Slf4j
public class SemaphoreMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 设置只让5个线程同时访问
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 20; i++) {
            final int NO = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        log.info("Acessing: NO = {}", NO);
                        Thread.sleep((long) (Math.random() * 10000));
                        // 访问完后，释放
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

}
