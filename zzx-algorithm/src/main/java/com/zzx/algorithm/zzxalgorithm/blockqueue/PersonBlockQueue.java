package com.zzx.algorithm.zzxalgorithm.blockqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouzhixiang
 * @Date 2020-12-13
 */
public class PersonBlockQueue {

    private List<Person> list = new ArrayList<>();
    private volatile int size;
    private volatile int capacity;
    private Lock lock = new ReentrantLock();
    private final Condition isNull = lock.newCondition();
    private final Condition isFull = lock.newCondition();

    public PersonBlockQueue(int capacity) {
        this.capacity = capacity;
    }

    public void add(Person p) {
        try {
            lock.lock();
            try {
                while (size >= capacity) {
                    System.out.println("阻塞队列塞满了");
                    isFull.await();
                }
            } catch (InterruptedException e) {
                isFull.signal();
                e.printStackTrace();
            }
            ++size;
            list.add(p);
            isNull.signal();
        } finally {
            lock.unlock();
        }
    }

    public Person take() {
        try {
            lock.lock();
            try {
                while (size == 0) {
                    System.out.println("阻塞队列空了");
                    isNull.await();
                }
            } catch (InterruptedException e) {
                isNull.signal();
                e.printStackTrace();
            }
            --size;
            Person res = list.get(0);
            list.remove(0);
            isFull.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }
}
