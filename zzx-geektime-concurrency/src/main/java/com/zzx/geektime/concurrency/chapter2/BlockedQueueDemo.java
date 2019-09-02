package com.zzx.geektime.concurrency.chapter2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p><em>BlockedQueue:</em>通过ReentrantLock、Condition实现阻塞队列</p>
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Zhou / zzx
 * @Date 2019-06-01 16:32
 **/
public class BlockedQueueDemo<T> {

    public static void main(String[] args) {
        ArrayBlockingQueue
    }

    private final Lock lock = new ReentrantLock();
    private Integer size;
    private final Integer CAPACITY = Integer.MAX_VALUE;
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    void enq(T x) {
        lock.lock();
        while (size > CAPACITY) {
            try {
                notEmpty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
