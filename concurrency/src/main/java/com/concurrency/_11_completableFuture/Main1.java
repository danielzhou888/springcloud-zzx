package com.concurrency._11_completableFuture;

import lombok.extern.slf4j.Slf4j;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhixiang
 * @Date 2020-12-05
 */
@Slf4j
public class Main1 {

    /**
     * 获取股票价格
     */
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1), new MyNamedThreadFactory("test-2"));

        CompletableFuture<Double> fetchPriceFuture = CompletableFuture.supplyAsync(Main1::fetchPrice, poolExecutor);
        fetchPriceFuture.thenAccept((price) -> {
            log.info("price = {}", price);
        }).exceptionally((e) -> {
            log.error("exception {}", e);
            return null;
        });
        fetchPriceFuture.join();
        System.out.println("end");
    }

    public static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

}
