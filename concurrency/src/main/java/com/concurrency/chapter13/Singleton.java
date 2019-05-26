package com.concurrency.chapter13;

import java.util.stream.IntStream;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-21 21:22
 */
public class Singleton {

    private static Singleton singleton = null;

    private Singleton() {
        System.out.println("Hi I'm Contructor! ");
    }

    /**
     * DCL（Double Check Lock 双端检锁机制）
     * @return
     */
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton ==  null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 1000).forEach(i -> {
                new Thread(() -> {
                    Singleton.getInstance();
                }, String.valueOf(i)).start();
        });
    }
}
