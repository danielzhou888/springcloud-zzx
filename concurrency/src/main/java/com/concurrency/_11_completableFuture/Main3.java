package com.concurrency._11_completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouzhixiang
 * @Date 2020-12-05
 */
@Slf4j
public class Main3 {

    public static void main(String[] args) {

//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor()

        CompletableFuture<String> zgsyFuture = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        });
        CompletableFuture<String> zgshFuture = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石化");
        });
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(zgsyFuture, zgshFuture);
        CompletableFuture<Double> sinaFuture = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice(String.valueOf(code), "sina");
        });
        CompletableFuture<Double> i63Future = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice(String.valueOf(code), "163");
        });

        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(sinaFuture, i63Future);
        cfFetch.thenAccept((result) -> {
            log.info("price = {}", result);
        }).join();
    }

    public static Double fetchPrice(String code, String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        if (Math.random() < 0.3) {
//            throw new RuntimeException("fetch price failed!");
//        }
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
