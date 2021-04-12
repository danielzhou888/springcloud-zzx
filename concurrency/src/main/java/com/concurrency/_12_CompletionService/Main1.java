package com.concurrency._12_CompletionService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhouzhixiang
 * @Date 2020-12-05
 */
public class Main1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        cs.submit(() -> getPriceByS1());
        cs.submit(() -> getPriceByS2());
        cs.submit(() -> getPriceByS3());

        for (int i = 0; i < 3; i++) {
            Integer r = cs.take().get();
            executor.execute(() -> save(r));
        }

    }

    static Integer getPriceByS1() {
        return 1;
    }

    static Integer getPriceByS2() {
        return 2;
    }

    static Integer getPriceByS3() {
        return 3;
    }

    static void save(Integer r) {

    }
}
