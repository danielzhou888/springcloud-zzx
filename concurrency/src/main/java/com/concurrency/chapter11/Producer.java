package com.concurrency.chapter11;

import java.util.Queue;
import java.util.Random;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-17 10:05
 */
public class Producer implements Runnable {
    private Queue<Integer> lianjingtaiQueue;
    private int maxSize;

    public Producer(Queue<Integer> queue, int maxSize){
        this.lianjingtaiQueue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lianjingtaiQueue) {
                while (lianjingtaiQueue.size() == maxSize) {
                    try {
                        System.out.println("lianjingtaiQueue is Full");
                        lianjingtaiQueue.wait();
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
                Random random = new Random();
                int i = random.nextInt();
                System.out.println("Produce " + i);
                lianjingtaiQueue.add(i);
                lianjingtaiQueue.notifyAll();
            }
        }
    }
}
