package com.zzx.dynamic.thread.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * 可配置的线程池
 * @author zhouzhixiang
 * @Date 2021-04-10
 */
public interface ConfigurableExecutor extends Executor {

    /**
     * 修改核心线程数
     * @param corePoolSize
     */
    void setCorePoolSize(int corePoolSize);

    /**
     * 修改最大线程数
     * @param maximumPoolSize
     */
    void setMaximumPoolSize(int maximumPoolSize);

    /**
     * 修改空闲保持时间
     * @param time
     * @param unit
     */
    void setKeepAliveTime(long time, TimeUnit unit);

    /**
     * 修改工作队列容量
     * @param workQueueCapacity
     */
    void setWorkQueueCapacity(int workQueueCapacity);
}
