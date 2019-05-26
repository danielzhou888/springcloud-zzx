package com.concurrency.chapter11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-17 10:05
 */
public class Main {
    public static void main(String[] args){
        Queue<Integer> lianjintaiQueue = new LinkedList<>();
        int maxSize = 10;
        Producer p = new Producer(lianjintaiQueue, maxSize);
        Consumer c = new Consumer(lianjintaiQueue, maxSize);
        Thread pT = new Thread(p);
        Thread pC = new Thread(c);
        pT.start();
        pC.start();
    }
}
