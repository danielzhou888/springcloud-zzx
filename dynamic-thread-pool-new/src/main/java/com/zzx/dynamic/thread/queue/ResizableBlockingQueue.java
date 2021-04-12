package com.zzx.dynamic.thread.queue;

import java.util.concurrent.BlockingQueue;

/**
 * @author zhouzhixiang
 * @Date 2021-04-10
 */
public interface ResizableBlockingQueue<E> extends BlockingQueue<E> {

    /**
     * 修改队列容量
     * @param capacity
     */
    void setCapacity(int capacity);
}
