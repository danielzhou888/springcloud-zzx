package com.concurrency.chapter17;

import java.util.concurrent.Semaphore;

/**
 * @program: springcloud-zzx
 *
 * @description:
 *
 * @author: zhouzhixiang
 *
 * @create: 2019-05-16 20:04
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(5, true);
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "获取了许可，还剩余"+semaphore.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                        System.out.println(Thread.currentThread().getName() + "释放了许可，还剩余" + semaphore.availablePermits());
                    }
                }
            }).start();
        }
    }
}
