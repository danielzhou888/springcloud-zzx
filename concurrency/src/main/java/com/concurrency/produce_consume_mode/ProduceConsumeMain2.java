package com.concurrency.produce_consume_mode;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouzhixiang
 * @Date 2020-08-08
 */
@Slf4j
public class ProduceConsumeMain2 {

    private LinkedList<String> inventory = new LinkedList<>();

    private AtomicInteger inventoryCnt = new AtomicInteger();

    private int maxInventory = 10;

    private Lock productLock = new ReentrantLock();

    private Lock consumeLock = new ReentrantLock();

    private Condition notFullCondition = productLock.newCondition();

    private Condition notEmptyCondition = consumeLock.newCondition();

    public void produce(String p) {
        productLock.lock();
        try {
            while (inventoryCnt.get() == maxInventory) {
                notFullCondition.await();
            }
            inventory.add(p);
            log.info("放入一个商品， 总库存 = {}", inventoryCnt.incrementAndGet());
            if (inventoryCnt.get() < maxInventory) {
                notFullCondition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            productLock.unlock();
        }

        if (inventoryCnt.get() > 0) {
            try {
                consumeLock.lockInterruptibly();
                notEmptyCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                consumeLock.unlock();
            }
        }
    }

    public void consume() {
        consumeLock.lock();
        try {
            while (inventoryCnt.get() == 0) {
                notEmptyCondition.await();
            }
            inventory.removeLast();
            log.info("消费一个商品， 商品总库存 = {}", inventoryCnt.decrementAndGet());
            if (inventoryCnt.get() > 0) {
                notEmptyCondition.signalAll();
            }
        } catch(InterruptedException e) {
          e.printStackTrace();
        } finally {
            consumeLock.unlock();
        }

        if (inventoryCnt.get() < maxInventory) {
            try {
                productLock.lockInterruptibly();
                notFullCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                productLock.unlock();
            }
        }

    }

    private class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                produce("商品"+i);
            }
        }
    }

    private class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                consume();
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumeMain2 pc = new ProduceConsumeMain2();
        new Thread(pc.new Producer()).start();
        new Thread(pc.new Consumer()).start();
        new Thread(pc.new Producer()).start();
        new Thread(pc.new Consumer()).start();
    }
}
