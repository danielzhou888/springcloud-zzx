package com.zzx.dynamic.thread.factory;

import com.zzx.dynamic.thread.executor.DdkyExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public abstract class AbstractDdkyExecutorFactory implements DdkyExecutorFactory {

    private final ConcurrentMap<String, DdkyExecutor> cachedExecutors = new ConcurrentHashMap<>();

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
        }
    }

    /**
     * 创建线程池，实现逻辑交给子类
     * @param poolName
     * @return
     */
    protected abstract DdkyExecutor createExecutor(String poolName);
}
