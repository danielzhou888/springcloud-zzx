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
     * 根据线程池名称获取线程池
     * @param poolName
     * @return
     */
    DdkyExecutor getExecutor(String poolName);

    /**
     * 关闭所有线程池
     * @param timeout
     * @param timeUnit
     */
    void shutdown(long timeout, TimeUnit timeUnit);
}
