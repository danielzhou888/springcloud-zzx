package com.zzx.dynamic.thread;

import com.zzx.dynamic.thread.task.DdkyExecutorTask;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 叮当复合弹性线程池
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public interface DdkyExecutors {

    /**
     * 根据任务上下文，提交任务到某一个线程池
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
    void shutdown(int timeout, TimeUnit timeUnit);
}
