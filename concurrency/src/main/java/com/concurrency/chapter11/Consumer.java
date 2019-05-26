package com.concurrency.chapter11;

import java.util.Queue;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-17 10:05
 */
public class Consumer implements Runnable {

    private Queue<Integer> lianjingtaiQueue;
    private int maxSize;

    public Consumer(Queue<Integer> queue, int maxSize){
        this.lianjingtaiQueue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true){
            synchronized (lianjingtaiQueue){
                while (lianjingtaiQueue.isEmpty()){
                    System.out.println("炼金台 is Empty");
                    try{
                        lianjingtaiQueue.wait();
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                }
                int v = lianjingtaiQueue.remove();
                System.out.println("Consume " + v);
                lianjingtaiQueue.notifyAll();
            }
        }
    }
}
