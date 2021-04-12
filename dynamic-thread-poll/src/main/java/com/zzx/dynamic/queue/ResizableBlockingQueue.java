package com.zzx.dynamic.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 容量可变的队列
 * @author zhouzhixiang
 * @param <E>
 */
public interface ResizableBlockingQueue<E> extends BlockingQueue<E> {

    /**
     * 修改容量
     * @param capacity
     */
    void setCapacity(int capacity);

}
