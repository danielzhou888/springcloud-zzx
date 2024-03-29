package com.zzx.dynamic.thread.factory;

import com.zzx.dynamic.thread.executor.DdkyExecutor;
import com.zzx.dynamic.thread.executor.DefaultDdkyThreadPoolExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * 默认线程池工厂
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public class DefaultDdkyExecutorFactory extends AbstractDdkyExecutorFactory {

    /** 普通线程池缓存容器 **/
    protected static final ConcurrentMap<String, DdkyExecutor> cachedExecutors = new ConcurrentHashMap<>();

    public static ConcurrentMap<String, DdkyExecutor> getCachedExecutorsMap() {
        return cachedExecutors;
    }

    @Override
    public DdkyExecutor createExecutor(String poolName) {
        return DefaultDdkyThreadPoolExecutor.createExecutor(poolName);
    }

    @Override
    public DdkyExecutor createCachedExecutor(String poolName) {
        return DefaultDdkyThreadPoolExecutor.createCachedExecutor(poolName);
    }

    @Override
    public void shutdown(long timeout, TimeUnit timeUnit) {
        for(Map.Entry<String, DdkyExecutor> entry : cachedExecutors.entrySet()) {
            DdkyExecutor executor = entry.getValue();
            executor.shutdown(timeout, timeUnit);
            cachedExecutors.remove(entry.getKey());
        }
    }
}
