package com.zzx.dynamic.thread.executor;

import com.zzx.dynamic.thread.task.DdkyExecutorTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 叮当动态线程池，默认使用jdk线程池实现
 * @author zhouzhixiang
 * @Date 2021-04-10
 */
public interface DdkyExecutor extends ConfigurableExecutor, MonitoringExecutor, ExecutorService {

    /**
     * 默认线程池名称
     */
    String DEFAULT_POOL_NAME = "default-executor";

    /**
     * 提交任务到本地线程池
     * @param task
     * @param <V>
     * @return
     */
    <V> Future<V> submit(DdkyExecutorTask<V> task);

    /**
     * 关闭线程池
     * @param timeout
     * @param timeUnit
     */
    void shutdown(long timeout, TimeUnit timeUnit);
}
