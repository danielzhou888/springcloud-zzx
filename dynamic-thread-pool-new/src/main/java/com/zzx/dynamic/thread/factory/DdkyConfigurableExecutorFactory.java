package com.zzx.dynamic.thread.factory;

import com.zzx.dynamic.thread.executor.ConfigurableThreadPoolExecutor;
import com.zzx.dynamic.thread.executor.DdkyExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * 默认线程池工厂
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public class DdkyConfigurableExecutorFactory extends AbstractDdkyExecutorFactory {

    /** 动态线程池缓存容器 **/
    protected static final ConcurrentMap<String, DdkyExecutor> configurableCachedExecutors = new ConcurrentHashMap<>();

    public static ConcurrentMap<String, DdkyExecutor> getCachedExecutorsMap() {
        return configurableCachedExecutors;
    }

    @Override
    public DdkyExecutor createExecutor(String poolName) {
        return ConfigurableThreadPoolExecutor.createExecutor(poolName);
    }

    @Override
    public DdkyExecutor createCachedExecutor(String poolName) {
        return ConfigurableThreadPoolExecutor.createCachedExecutor(poolName);
    }

    @Override
    public void shutdown(long timeout, TimeUnit timeUnit) {
        for(Map.Entry<String, DdkyExecutor> entry : configurableCachedExecutors.entrySet()) {
            DdkyExecutor executor = entry.getValue();
            executor.shutdown(timeout, timeUnit);
            configurableCachedExecutors.remove(entry.getKey());
        }
    }
}
