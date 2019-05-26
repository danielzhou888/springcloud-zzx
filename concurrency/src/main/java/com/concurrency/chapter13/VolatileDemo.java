package com.concurrency.chapter13;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-21 19:55
 */
public class VolatileDemo {

    volatile int n = 0;

    public void add() {
        n++;
    }

    public static void main(String[] args) {

    }


}
