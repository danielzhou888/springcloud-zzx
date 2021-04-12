package com.zzx.dynamic.thread.config;

import com.zzx.dynamic.thread.executor.DdkyExecutor;
import com.zzx.dynamic.thread.executor.NamedThreadFactory;
import com.zzx.dynamic.thread.util.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * 线程池配置
 * @author zhouzhixiang
 * @Date 2021-04-09
 */
public class DdkyExecutorProperty {

    // cpu core
    private static final int CPU_PROCESSOR_SIZE = Runtime.getRuntime().availableProcessors();
    // 线程池名称
    protected static final String DEFAULT_POOL_NAME = DdkyExecutor.DEFAULT_POOL_NAME;
    // 核心线程数
    protected static final int DEFAULT_CORE_POOL_SIZE = CPU_PROCESSOR_SIZE;
    // 最大线程池
    protected static final int DEFAULT_MAXIMUM_POOL_SIZE = 2 * CPU_PROCESSOR_SIZE + 1;
    // 空闲保持时间
    protected static final int DEFAULT_KEEP_ALIVE_TIME = 6000;
    // 阻塞队列容量
    protected static final int DEFAULT_QUEUE_CAPACITY = 200;
    // 阻塞队列类型
    protected static final String DEFAULT_WORK_QUEUE_TYPE = LinkedBlockingDeque.class.getSimpleName();
    // 拒绝策略类型
    protected static final String DEFAULT_REJECTED_HANDLER_TYPE = ThreadPoolExecutor.AbortPolicy.class.getSimpleName();
    // 默认选择器表达式
    protected static final String DEFAULT_SELECTOR_EXPRESSION = "";

    private static final String POOL_NAME = "poolName";
    private static final String CORE_POOL_SIZE = "corePoolSize";
    private static final String MAXIMUM_POOL_SIZE = "maximumPoolSize";
    private static final String KEEP_ALIVE_TIME = "keepAliveTime";
    private static final String QUEUE_CAPACITY = "queueCapacity";
    private static final String WORK_QUEUE_TYPE = "workQueueType";
    private static final String REJECTED_HANDLER_TYPE = "rejectedHandlerType";
    private static final String SELECTOR_EXPRESSION = "expression";

    private final Map<String, String> properties;
    private static final Map<String, BlockingQueue<Runnable>> BLOCKING_QUEUES = new HashMap<>();
    private static final Map<String, RejectedExecutionHandler> REJECTED_HANDLERS = new HashMap<>();

    private final ThreadFactory threadFactory;

    public DdkyExecutorProperty() {
        properties = new HashMap<>();
        properties.put(POOL_NAME, DEFAULT_POOL_NAME);
        properties.put(CORE_POOL_SIZE, DEFAULT_CORE_POOL_SIZE + "");
        properties.put(MAXIMUM_POOL_SIZE, DEFAULT_MAXIMUM_POOL_SIZE + "");
        properties.put(KEEP_ALIVE_TIME, DEFAULT_KEEP_ALIVE_TIME + "");
        properties.put(QUEUE_CAPACITY, DEFAULT_QUEUE_CAPACITY + "");
        properties.put(WORK_QUEUE_TYPE, DEFAULT_WORK_QUEUE_TYPE);
        properties.put(REJECTED_HANDLER_TYPE, DEFAULT_REJECTED_HANDLER_TYPE);
        properties.put(SELECTOR_EXPRESSION, DEFAULT_SELECTOR_EXPRESSION);

        BLOCKING_QUEUES.put(LinkedBlockingDeque.class.getSimpleName(), new LinkedBlockingDeque<>());
        BLOCKING_QUEUES.put(ArrayBlockingQueue.class.getSimpleName(), new ArrayBlockingQueue<>(getQueueCapacity()));
        BLOCKING_QUEUES.put(SynchronousQueue.class.getSimpleName(), new SynchronousQueue<>());
        BLOCKING_QUEUES.put(LinkedTransferQueue.class.getSimpleName(), new LinkedTransferQueue<>());
        BLOCKING_QUEUES.put(PriorityBlockingQueue.class.getSimpleName(), new PriorityBlockingQueue<>(getQueueCapacity()));
        BLOCKING_QUEUES.put(DelayQueue.class.getSimpleName(), new DelayQueue());

        REJECTED_HANDLERS.put("AbortPolicy", new ThreadPoolExecutor.AbortPolicy());
        REJECTED_HANDLERS.put("DiscardPolicy", new ThreadPoolExecutor.DiscardPolicy());
        REJECTED_HANDLERS.put("DiscardOldestPolicy", new ThreadPoolExecutor.DiscardOldestPolicy());
        REJECTED_HANDLERS.put("CallerRunsPolicy", new ThreadPoolExecutor.CallerRunsPolicy());

        threadFactory = new NamedThreadFactory(getPoolName());
    }

    public String getPoolName() {
        return Strings.blankDefault(properties.get(POOL_NAME), DEFAULT_POOL_NAME);
    }

    public int getQueueCapacity() {
        return Strings.blankDefaultInt(properties.get(QUEUE_CAPACITY), DEFAULT_QUEUE_CAPACITY);
    }
    
    public int getCorePoolSize() {
        return Strings.blankDefaultInt(properties.get(CORE_POOL_SIZE), DEFAULT_CORE_POOL_SIZE);
    }
    
    public int getMaximumPoolSize() {
        return Strings.blankDefaultInt(properties.get(MAXIMUM_POOL_SIZE), DEFAULT_MAXIMUM_POOL_SIZE);
    }

    public long getKeepAliveTime() {
        return Strings.blankDefaultLong(properties.get(KEEP_ALIVE_TIME), DEFAULT_KEEP_ALIVE_TIME);
    }

    public String getWorkQueueType() {
        return Strings.blankDefault(properties.get(WORK_QUEUE_TYPE), DEFAULT_WORK_QUEUE_TYPE);
    }

    public BlockingQueue<Runnable> getWorkQueue() {
        return BLOCKING_QUEUES.get(getWorkQueueType());
    }

    public String getRejectedHandlerType() {
        return Strings.blankDefault(properties.get(REJECTED_HANDLER_TYPE), DEFAULT_REJECTED_HANDLER_TYPE);
    }

    public RejectedExecutionHandler getRejectedHandler() {
        return REJECTED_HANDLERS.get(getRejectedHandlerType());
    }

    public String getExpression() {
        return Strings.blankDefault(properties.get(SELECTOR_EXPRESSION), DEFAULT_SELECTOR_EXPRESSION);
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void addProperty(String key, String value) {
        this.properties.put(key, value);
    }

    static DdkyExecutorProperty named(String poolName) {
        return new NamedDdkyExecutorProperty(poolName);
    }

    static class NamedDdkyExecutorProperty extends DdkyExecutorProperty {
        public NamedDdkyExecutorProperty(String poolName) {
            super();
            addProperty(POOL_NAME, poolName);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DdkyExecutorProperty)) {
            return false;
        }
        DdkyExecutorProperty other = (DdkyExecutorProperty) o;
        return this.getPoolName().equals(other.getPoolName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPoolName());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("DdkyExecutorProperty[");
        builder.append("poolName=").append(getPoolName()).append(", ")
                .append("corePoolSize=").append(getCorePoolSize()).append(", ")
                .append("maximumPoolSize=").append(getMaximumPoolSize()).append(", ")
                .append("keepAliveTime=").append(getKeepAliveTime()).append(", ")
                .append("workQueueType=").append(getWorkQueueType()).append(", ")
                .append("queueCapacity=").append(getQueueCapacity()).append(", ")
                .append("rejectedHandlerType=").append(getRejectedHandlerType()).append(", ")
                .append("expression=").append(getExpression()).append(", ")
                .append("]");
        return builder.toString();
    }
}
