package com.concurrency.a1自定义线程池拒绝策略;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main1 {

    /**
     * 使用自定义线程池拒绝策略
     */
    public void test1() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 10, 6, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), new MyNamedThreadFactory("test1_"), new AbortPolicyWithReport("test1_", null));
        // ...
    }

}
