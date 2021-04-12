package com.zzx.dynamic.thread.executor;

import com.zzx.dynamic.thread.queue.ResizableBlockingQueue;
import com.zzx.dynamic.thread.task.DdkyExecutorTask;
import com.zzx.dynamic.thread.task.DdkyTaskRejectedException;
import com.zzx.dynamic.thread.util.Asserts;
import com.zzx.dynamic.thread.util.NetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * 叮当默认线程池执行器
 * @author zhouzhixiang
 * @Date 2021-04-10
 */
public class DefaultDdkyThreadPoolExecutor extends ThreadPoolExecutor implements DdkyExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDdkyThreadPoolExecutor.class);

    private final String poolName;

    private int workQueueCapacity;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static DdkyExecutor form(String poolName) {
        return Builder.fromName(poolName);
    }

    private DefaultDdkyThreadPoolExecutor(String poolName, int corePoolSize, int maximumPoolSize, long keepAliveTime, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue, threadFactory, new RejectedExecutionHandlerWrapper(rejectedExecutionHandler));
        this.poolName = poolName;
        // 由于队列是刚创建，所以剩余容量=容量
        this.workQueueCapacity = workQueue.remainingCapacity();
    }

    public static class Builder {

        private static final int CPU_PROCESSOR_SIZE = Runtime.getRuntime().availableProcessors();

        private String poolName = DdkyExecutor.DEFAULT_POOL_NAME;

        private int corePoolSize = CPU_PROCESSOR_SIZE;

        private int maximumPoolSize = 2 * CPU_PROCESSOR_SIZE + 1;

        private long keepAliveTime = 6000;

        private int capacity = 200;

        private BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(capacity);

        private ThreadFactory threadFactory = new NamedThreadFactory(poolName);

        private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

        private Builder() {}

        public Builder poolName(String poolName) {
            Asserts.notEmpty(poolName, "poolName is Empty");
            this.poolName = poolName;
            return this;
        }

        public Builder corePoolSize(int corePoolSize) {
            Asserts.isFalse(corePoolSize < 0, "corePoolSize <= 0");
            this.corePoolSize = corePoolSize;
            return this;
        }

        public Builder maximumPoolSize(int maximumPoolSize) {
            Asserts.isFalse(maximumPoolSize <= 0 || maximumPoolSize < corePoolSize, "maximumPoolSize ,= 0 || maxmumPoolSize < corePoolSize");
            this.maximumPoolSize = maximumPoolSize;
            return this;
        }

        public Builder keepAliveTime(long keepAliveTime) {
            Asserts.isFalse(keepAliveTime <= 0, "keepAliveTime <= 0");
            this.keepAliveTime = keepAliveTime;
            return this;
        }

        public Builder workQueue(BlockingQueue<Runnable> workQueue) {
            Asserts.notNull(workQueue, "workQueue == null");
            this.workQueue = workQueue;
            return this;
        }

        public Builder threadFactory(ThreadFactory threadFactory) {
            Asserts.notNull(threadFactory, "threadFactory == null");
            this.threadFactory = threadFactory;
            return this;
        }

        public Builder rejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
            Asserts.notNull(rejectedExecutionHandler, "rejectedExecutionHandler == null");
            this.rejectedExecutionHandler = rejectedExecutionHandler;
            return this;
        }

        public DdkyExecutor builder() {
            return new DefaultDdkyThreadPoolExecutor(poolName, corePoolSize, maximumPoolSize, keepAliveTime, workQueue, threadFactory, rejectedExecutionHandler);
        }

        private static DdkyExecutor fromName(String poolName) {
            return new Builder().poolName(poolName).builder();
        }

    }


    private static class RejectedExecutionHandlerWrapper extends LongAdder implements RejectedExecutionHandler {

        private final RejectedExecutionHandler policy;

        public RejectedExecutionHandlerWrapper(RejectedExecutionHandler policy) {
            this.policy = policy;
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            this.increment();
            try {
                policy.rejectedExecution(r, executor);
            } catch (RejectedExecutionException ex) {
                throw new DdkyTaskRejectedException(ex);
            }
            throw new DdkyTaskRejectedException();
        }

        public long getRejectedCount() {
            return this.longValue();
        }
    }

    @Override
    public <V> Future<V> submit(DdkyExecutorTask<V> task) {
        if (task == null) {
            throw new NullPointerException();
        }
        RunnableFuture<V> futureTask = newTaskFor(task);
        try {
            execute(futureTask);
        } catch (DdkyTaskRejectedException ex) {
            task.changeState(DdkyExecutorTask.TaskState.REJECTED);
            if (ex.existRejectedExecutionEception()) {
                throw ex.getRejectedExecutionException();
            }
        } catch (Throwable ex) {
            task.changeState(DdkyExecutorTask.TaskState.FAILURE);
        } finally {
            task.destroy();
        }
        return futureTask;
    }

    @Override
    public void shutdown(long timeout, TimeUnit timeUnit) {
        this.shutdown();
        try {
            boolean terminated = awaitTermination(timeout, timeUnit);
            if (terminated) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("线程池{}关闭成功", poolName);
                }
            } else {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("在{}{}内，线程池{}没有成功关闭", timeout, timeUnit, poolName);
                }
            }
        } catch (InterruptedException ex) {
            LOGGER.error("线程池{}关闭过程中f发生中断异常", poolName);
        }
    }

    @Override
    public void setThreadFactory(ThreadFactory threadFactory) {
        super.setThreadFactory(threadFactory);
    }

    @Override
    public void setRejectedExecutionHandler(RejectedExecutionHandler handler) {
        super.setRejectedExecutionHandler(handler);
    }

    @Override
    public void setCorePoolSize(int corePoolSize) {
        int oldCorePoolSize = getCorePoolSize();
        if (corePoolSize == oldCorePoolSize) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("线程池{}：corePoolSize新值{}和旧值{}一样，不做修改更新操作", poolName, corePoolSize, oldCorePoolSize);
            }
        } else {
            super.setCorePoolSize(corePoolSize);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("线程池{}：corePoolSize修改已生效，新值：{}，旧值：{}", poolName, corePoolSize, oldCorePoolSize);
            }
        }
    }

    @Override
    public void setMaximumPoolSize(int maximumPoolSize) {
        int oldMaximumPoolSize = getMaximumPoolSize();
        if (oldMaximumPoolSize == maximumPoolSize) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("线程池{}：maximumPoolSize新值{}和旧值{}一样，不做修改更新操作", poolName, maximumPoolSize, oldMaximumPoolSize);
            }
        } else {
            super.setMaximumPoolSize(maximumPoolSize);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("线程池{}：maximumPoolSize修改已生效，新值：{}，旧值：{}", poolName, maximumPoolSize, oldMaximumPoolSize);
            }
        }
    }

    @Override
    public void setKeepAliveTime(long time, TimeUnit unit) {
        long oldKeepAliveTime = getKeepAliveTime(unit);
        if (oldKeepAliveTime == time) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("线程池{}：keepAliveTime新值{}和旧值{}一样，不做修改更新操作", poolName, time, oldKeepAliveTime);
            }
        } else {
            super.setKeepAliveTime(time, unit);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("线程池{}：keepAliveTime修改已生效，新值：{}，旧值：{}", poolName, time, oldKeepAliveTime);
            }
        }
    }

    @Override
    public void setWorkQueueCapacity(int newWorkQueueCapacity) {
        BlockingQueue<Runnable> workQueue = this.getQueue();
        if (newWorkQueueCapacity == workQueueCapacity) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("线程池{}：workQueueCapacity新值{}和旧值{}一样，不做修改更新操作", poolName, newWorkQueueCapacity, workQueueCapacity);
            }
        }
        if (workQueue instanceof ResizableBlockingQueue) {
            ((ResizableBlockingQueue<Runnable>) workQueue).setCapacity(newWorkQueueCapacity);
            this.workQueueCapacity = newWorkQueueCapacity;
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("线程池{}，工作队列{}，容量workQueueCapacity 已被修改生效，新值：{}，旧值：{}", poolName, workQueue.getClass().getSimpleName(), newWorkQueueCapacity, workQueueCapacity);
            }
        } else {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("线程池{}：工作队列{}不支持修改容量", poolName, workQueue.getClass().getSimpleName());
            }
        }
    }

    @Override
    public String getPoolName() {
        return this.poolName;
    }

    @Override
    public String getHost() {
        return NetUtils.getLocalHost();
    }

    @Override
    public int getActiveCount() {
        return super.getActiveCount();
    }

    @Override
    public RejectedExecutionHandler getRejectedExecutionHandler() {
        return super.getRejectedExecutionHandler();
    }

    @Override
    public int getCorePoolSize() {
        return super.getCorePoolSize();
    }

    @Override
    public int getMaximumPoolSize() {
        return super.getMaximumPoolSize();
    }

    @Override
    public long getKeepAliveTime(TimeUnit unit) {
        return super.getKeepAliveTime(unit);
    }

    @Override
    public int getPoolSize() {
        return super.getPoolSize();
    }

    @Override
    public int getLargestPoolSize() {
        return super.getLargestPoolSize();
    }

    @Override
    public long getCompletedTaskCount() {
        return super.getCompletedTaskCount();
    }

    @Override
    public BlockingQueue<Runnable> getWorkQueue() {
        return getQueue();
    }

    @Override
    public String getWorkQueueType() {
        return getQueue().getClass().getSimpleName();
    }

    @Override
    public int getWorkQueueCapacity() {
        return this.workQueueCapacity;
    }

    @Override
    public int getWorkQueueSize() {
        return getQueue().size();
    }

    @Override
    public int getRemainingCapacity() {
        return getQueue().remainingCapacity();
    }

    @Override
    public String getRejectedExecutionHandlerType() {
        return getRejectedExecutionHandlerType().getClass().getSimpleName();
    }

    @Override
    public long getRejectedTaskCount() {
        return ((RejectedExecutionHandlerWrapper)getRejectedExecutionHandler()).getRejectedCount();
    }
}
