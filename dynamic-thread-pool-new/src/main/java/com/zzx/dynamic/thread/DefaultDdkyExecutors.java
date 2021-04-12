package com.zzx.dynamic.thread;

import com.zzx.dynamic.thread.executor.DdkyExecutor;
import com.zzx.dynamic.thread.factory.DdkyExecutorFactory;
import com.zzx.dynamic.thread.factory.DefaultDdkyExecutorFactory;
import com.zzx.dynamic.thread.selector.DefaultExecutorSelector;
import com.zzx.dynamic.thread.selector.ExecutorSelector;
import com.zzx.dynamic.thread.task.DdkyExecutorTask;
import com.zzx.dynamic.thread.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 叮当复合线程池默认实现
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public class DefaultDdkyExecutors implements DdkyExecutors {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDdkyExecutors.class);

    private ExecutorSelector executorSelector = new DefaultExecutorSelector();

    private DdkyExecutorFactory ddkyExecutorFactory = new DefaultDdkyExecutorFactory();

    @Override
    public <V> Future<V> submit(DdkyExecutorTask<V> task) {
        Asserts.notNull(task, "task == null");
        String poolName = executorSelector.selectPoolKey(task.getTaskContext());
        DdkyExecutor executor = ddkyExecutorFactory.getExecutor(poolName);
        return executor.submit(task);
    }

    /**
     * 默认关闭工厂创建的所有线程池
     * @param timeout
     * @param timeUnit
     */
    @Override
    public void shutdown(int timeout, TimeUnit timeUnit) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("开始关闭叮当复合弹性线程池，等待时间：{}, 单位：{}", timeout, timeUnit);
        }
        ddkyExecutorFactory.shutdown(timeout, timeUnit);
    }
}
