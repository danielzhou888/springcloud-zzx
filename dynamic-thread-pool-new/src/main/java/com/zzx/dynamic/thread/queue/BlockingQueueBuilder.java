package com.zzx.dynamic.thread.queue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public class BlockingQueueBuilder<E> {

    /** 阻塞队列缓存 **/
    private static final Map<String, Class<?>> BLOCKING_QUEUE_CLASS_CACHE = new HashMap<>(8);

    /** 可变容量的阻塞队列（linked） **/
    private static final String RESIZABLE_LINKED_NAME = ResizableLinkedBlockingQueue.class.getSimpleName();

    private static final String ARRAY_NAME = ArrayBlockingQueue.class.getSimpleName();
    private static final String LINKED_NAME = LinkedBlockingDeque.class.getSimpleName();
    private static final String PRIORITY_NAME = PriorityBlockingQueue.class.getSimpleName();
    private static final String DELAY_NAME = DelayQueue.class.getSimpleName();
    private static final String SYNCHRONOUS_NAME = SynchronousQueue.class.getSimpleName();
    private static final String LINKED_TRANSFER_NAME = LinkedTransferQueue.class.getSimpleName();
    private static final String LINKED_DEQUE_NAME = LinkedBlockingDeque.class.getSimpleName();

    static {
        BLOCKING_QUEUE_CLASS_CACHE.put(ARRAY_NAME, ArrayBlockingQueue.class);
        BLOCKING_QUEUE_CLASS_CACHE.put(LINKED_NAME, LinkedBlockingQueue.class);
        BLOCKING_QUEUE_CLASS_CACHE.put(PRIORITY_NAME, PriorityBlockingQueue.class);
        BLOCKING_QUEUE_CLASS_CACHE.put(DELAY_NAME, DelayQueue.class);
        BLOCKING_QUEUE_CLASS_CACHE.put(SYNCHRONOUS_NAME, SynchronousQueue.class);
        BLOCKING_QUEUE_CLASS_CACHE.put(LINKED_TRANSFER_NAME, LinkedTransferQueue.class);
        BLOCKING_QUEUE_CLASS_CACHE.put(LINKED_DEQUE_NAME, LinkedBlockingDeque.class);

        BLOCKING_QUEUE_CLASS_CACHE.put(RESIZABLE_LINKED_NAME, ResizableLinkedBlockingQueue.class);
    }

    /** 队列类型：默认可变队列 **/
    private String type = RESIZABLE_LINKED_NAME;

    /**
     * 队列容量
     */
    private int capacity;

    /**
     * 公平性，只对ArrayBlockingQueue有效
     */
    private boolean fair;

    public BlockingQueueBuilder<E> capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public BlockingQueueBuilder<E> type(String type) {
        this.type = type;
        return this;
    }

    /**
     * 只针对ArrayBlockingQueue
     * @param fair
     * @return
     */
    public BlockingQueueBuilder<E> fair(boolean fair) {
        this.fair = fair;
        return this;
    }

    public BlockingQueue<E> build() {
        return createBlockingQueue();
    }

    private BlockingQueue<E> createBlockingQueue() {
        if (type.equals(RESIZABLE_LINKED_NAME)) {
            return new ResizableLinkedBlockingQueue<>(capacity);
        } else if(type.equals(ARRAY_NAME)) {
            return new ArrayBlockingQueue<>(capacity, fair);
        } else if(type.equals(LINKED_NAME)) {
            return new LinkedBlockingQueue<>(capacity);
        } else if(type.equals(PRIORITY_NAME)) {
            return new PriorityBlockingQueue<>(capacity);
        } else if(type.equals(DELAY_NAME)) {
            return new DelayQueue();
        } else if(type.equals(SYNCHRONOUS_NAME)) {
            return new SynchronousQueue<>();
        } else if(type.equals(LINKED_TRANSFER_NAME)) {
            return new LinkedTransferQueue<>();
        } else if(type.equals(LINKED_DEQUE_NAME)) {
            return new LinkedBlockingDeque<>(capacity);
        }
        return new ResizableLinkedBlockingQueue<>(capacity);
    }

}
