package com.concurrency.produce_consume_mode;

import com.concurrency.chapter11.Producer;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouzhixiang
 * @Date 2020-08-08
 */
@Slf4j
public class ProduceConsumeMain1 {

    private LinkedList<String> inventory = new LinkedList<>();

    private int maxInventory = 10;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void produce(String p) {
        lock.lock();
        try {
            while (inventory.size() == maxInventory) {
                condition.await();
            }
            inventory.add(p);
            log.info("放入一个商品库存，商品总库存 = {}", inventory.size());
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();

        try {
            while (inventory.size() == 0) {
                condition.await();
            }
            inventory.removeLast();
            log.info("消费一个商品库存，商品总库存 = {}", inventory.size());
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
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
        ProduceConsumeMain1 pc = new ProduceConsumeMain1();
        new Thread(pc.new Producer()).start();
        new Thread(pc.new Consumer()).start();
        new Thread(pc.new Producer()).start();
        new Thread(pc.new Consumer()).start();
    }
}
