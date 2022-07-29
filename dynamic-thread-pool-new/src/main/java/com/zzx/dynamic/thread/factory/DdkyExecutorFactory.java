package com.zzx.dynamic.thread.factory;

import com.zzx.dynamic.thread.executor.DdkyExecutor;

import java.util.concurrent.TimeUnit;

/**
 * 叮当线程池工厂
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public interface DdkyExecutorFactory {

    /**
     * 创建线程池（不对线程池进行缓存）
     * @param poolName
     * @return
     */
    DdkyExecutor createExecutor(String poolName);

    /**
     * 创建线程池(对线程池进行缓存)
     * @param poolName
     * @return
     */
    DdkyExecutor createCachedExecutor(String poolName);

    /**
     * 关闭缓存中所有线程池
     * @param timeout
     * @param timeUnit
     */
    void shutdown(long timeout, TimeUnit timeUnit);
}
