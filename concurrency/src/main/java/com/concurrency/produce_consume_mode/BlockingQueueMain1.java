package com.concurrency.produce_consume_mode;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>通过BlockingQueue阻塞队列，实现生产者消费者模式</p>
 * <p></p>
 * @author zhouzhixiang
 * @Date 2020-08-08
 */
@Slf4j
public class BlockingQueueMain1 {

    private int maxInventory = 10;

    private BlockingQueue<String> inventory = new LinkedBlockingQueue();

    public void produce(String p) {
        try {
            inventory.put(p);
            log.info("放入一个商品， 商品总库存 = {}", inventory.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        try {
            inventory.take();
            log.info("消费一个商品，商品总库存 = {}", inventory.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Producer implements Runnable {

        @Override
        public void run () {
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
        BlockingQueueMain1 bq = new BlockingQueueMain1();
        new Thread(bq.new Producer()).start();
        new Thread(bq.new Consumer()).start();
        new Thread(bq.new Producer()).start();
        new Thread(bq.new Consumer()).start();
    }
}
