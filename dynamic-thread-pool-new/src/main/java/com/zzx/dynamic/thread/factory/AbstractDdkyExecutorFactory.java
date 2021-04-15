package com.zzx.dynamic.thread.factory;

import com.zzx.dynamic.thread.executor.DdkyExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * 叮当抽象线程工厂
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public abstract class AbstractDdkyExecutorFactory implements DdkyExecutorFactory {

    private static final ConcurrentMap<String, DdkyExecutor> cachedExecutors = new ConcurrentHashMap<>();

    /**
     * 获取线程池执行器
     * 注意：1、当此线程池执行器已被创建时，直接从缓存取
     *      2、当此线程池首次创建时，放入缓存中，并返回创建的线程池执行器对象
     * @param poolName
     * @return
     */
    @Override
    public DdkyExecutor getExecutor(String poolName) {
        DdkyExecutor ddkyExecutor = cachedExecutors.get(poolName);
        if (ddkyExecutor == null) {
            synchronized (this) {
                ddkyExecutor = cachedExecutors.get(poolName);
                if (ddkyExecutor == null) {
                    ddkyExecutor = createExecutor(poolName);
                    cachedExecutors.putIfAbsent(poolName, ddkyExecutor);
                }
            }
        }
        return ddkyExecutor;
    }

    @Override
    public void shutdown(long timeout, TimeUnit timeUnit) {
        for(Map.Entry<String, DdkyExecutor> entry : cachedExecutors.entrySet()) {
            DdkyExecutor executor = entry.getValue();
            executor.shutdown(timeout, timeUnit);
            if (cachedExecutors.containsKey(entry.getKey())) {
                cachedExecutors.remove(entry.getKey());
            }
        }
    }

    @Override
    public DdkyExecutor createNewExecutor(String poolName) {
        return createExecutor(poolName);
    }

    /**
     * 创建线程池，实现逻辑交给子类
     * @param poolName
     * @return
     */
    protected abstract DdkyExecutor createExecutor(String poolName);

}
