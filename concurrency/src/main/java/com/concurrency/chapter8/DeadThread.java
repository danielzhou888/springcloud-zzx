package com.concurrency.chapter8;

/**
 * @program: scmd-knb-common
 * @description: 死锁测试案例
 * @author: zhouzhixiang
 * @create: 2019-03-22 17:14
 */
public class DeadThread implements Runnable {

    private String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void run() {

        while (true) {
            if (username.equals("a")) {
                synchronized (lock1) {
                    try {
                        System.out.println("username="+username);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("按lock1->lock2的顺序执行");
                    }
                }
            } else if (username.equals("b")) {
                synchronized (lock2) {
                    try {
                        System.out.println("username="+username);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println("按lock2->lock1的顺序执行");
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadThread d1 = new DeadThread();
        d1.setUsername("a");
        Thread t1 = new Thread(d1);

        t1.start();

        Thread.sleep(20);

        d1.setUsername("b");
        Thread t2 = new Thread(d1);
        t2.start();

    }
}
