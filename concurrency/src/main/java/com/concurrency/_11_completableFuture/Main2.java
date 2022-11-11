package com.concurrency._11_completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhixiang
 * @Date 2020-12-05
 */
@Slf4j
public class Main2 {

    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 3, 6, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), new MyNamedThreadFactory("test-1"));

        try {
            CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
                return queryCode("中国石油");
            }, poolExecutor);

            CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> {
                return fetchPrice(code);
            }, poolExecutor);

            cfFetch.thenAccept((result) -> {
                log.info("price = {}", result);
            }).join();
        } catch (Exception e) {
            log.error("exception {}", e);
        } finally {
            poolExecutor.shutdown();
        }


    }

    public static Double fetchPrice(String code) {
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

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {

        }
        return "601918";
    }
}
