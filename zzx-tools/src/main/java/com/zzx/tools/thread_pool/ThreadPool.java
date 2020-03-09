package com.zzx.tools.thread_pool;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2020-03-02
 */
public class ThreadPool {
    private static final Executor pool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2 - 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

}
