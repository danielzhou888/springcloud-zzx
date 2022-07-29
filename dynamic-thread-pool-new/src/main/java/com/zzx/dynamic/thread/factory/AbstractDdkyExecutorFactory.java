package com.zzx.dynamic.thread.factory;

import com.zzx.dynamic.thread.executor.DdkyExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 叮当抽象线程工厂
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public abstract class AbstractDdkyExecutorFactory implements DdkyExecutorFactory {

    @Override
    public abstract void shutdown(long timeout, TimeUnit timeUnit);

    /**
     * 创建线程池，实现逻辑交给子类
     * @param poolName
     * @return
     */
    @Override
    public abstract DdkyExecutor createExecutor(String poolName);

    /**
     * 创建线程池（对线程池进行缓存）
     * 注意：1、当此线程池执行器已被创建时，直接从缓存取
     *      2、当此线程池首次创建时，放入缓存中，并返回创建的线程池执行器对象
     * @param poolName
     * @return
     */
    @Override
    public abstract DdkyExecutor createCachedExecutor(String poolName);

}
