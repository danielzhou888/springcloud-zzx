package com.zzx.dynamic.thread.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * 受监控的线程池
 * @author zhouzhixiang
 * @Date 2021-04-10
 */
public interface MonitoringExecutor extends Executor {

    /**
     * 获取线程池名称
     * @return
     */
    String getPoolName();

    /**
     * 获取主机名
     * @return
     */
    String getHost();

    /**
     * 获取激活的任务数
     * @return
     */
    int getActiveCount();

    /**
     * 获取核心线程数
     * @return
     */
    int getCorePoolSize();

    /**
     * 获取最大线程数
     * @return
     */
    int getMaximumPoolSize();

    /**
     * 获取当前线程数
     * @return
     */
    int getPoolSize();

    /**
     * 获取历史最大线程数
     * @return
     */
    int getLargestPoolSize();

    /**
     * 获取已完成任务数
     * @return
     */
    long getCompletedTaskCount();

    /**
     * 获取阻塞队列
     * @return
     */
    BlockingQueue<Runnable> getWorkQueue();

    /**
     * 获取阻塞队列类型名称
     * @return
     */
    String getWorkQueueType();

    /**
     * 获取阻塞队列当前大小
     * @return
     */
    int getWorkQueueCapacity();

    /**
     * 获取阻塞队列剩余容量
     * @return
     */
    int getRemainingCapacity();

    /**
     * 获取拒绝策略
     * @return
     */
    RejectedExecutionHandler getRejectedExecutionHandler();

    /**
     * 获取拒绝策略类型
     * @return
     */
    String getRejectedExecutionHandlerType();

    /**
     * 获取拒绝的任务数
     * @return
     */
    long getRejectedTaskCount();

}
