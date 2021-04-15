package com.zzx.dynamic.thread.factory;

import com.zzx.dynamic.thread.config.DdkyExecutorProperty;
import com.zzx.dynamic.thread.config.DdkyExecutorsProperty;
import com.zzx.dynamic.thread.executor.DdkyExecutor;
import com.zzx.dynamic.thread.executor.DefaultDdkyThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 默认线程池工厂
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public class DefaultDdkyExecutorFactory extends AbstractDdkyExecutorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDdkyExecutorFactory.class);

    public static final ConcurrentMap<String, DdkyExecutor> executorsCached = new ConcurrentHashMap<>();

    @Override
    protected DdkyExecutor createExecutor(String poolName) {
        DdkyExecutorProperty ddkyExecutorProperty = DdkyExecutorsProperty.getDdkyExecutorProperty(poolName);
        if (ddkyExecutorProperty != null) {
            return DefaultDdkyThreadPoolExecutor.newBuilder()
                    .poolName(ddkyExecutorProperty.getPoolName())
                    .corePoolSize(ddkyExecutorProperty.getCorePoolSize())
                    .maximumPoolSize(ddkyExecutorProperty.getMaximumPoolSize())
                    .keepAliveTime(ddkyExecutorProperty.getKeepAliveTime())
                    .threadFactory(ddkyExecutorProperty.getThreadFactory())
                    .workQueue(ddkyExecutorProperty.getWorkQueue())
                    .rejectedExecutionHandler(ddkyExecutorProperty.getRejectedHandler())
                    .builder();
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("没有找到线程池{}, 将使用默认线程池进行任务处理", poolName);
        }
        return DefaultDdkyThreadPoolExecutor.createExecutor(poolName);
    }

    @Override
    public DdkyExecutor createNewExecutor(String poolName) {
        return null;
    }
}
