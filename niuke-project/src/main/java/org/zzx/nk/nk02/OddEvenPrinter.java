package org.zzx.nk.nk02;

/**
 * 5. 多线程打印奇偶数
 * 题目描述：使用两个线程交替打印1到100的奇数和偶数。
 */
public class OddEvenPrinter {
    private static final Object lock = new Object();
    private static int count = 1;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    if (count % 2 != 0) {
                        System.out.println("Odd: " + count);
                        count++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    if (count % 2 == 0) {
                        System.out.println("Even: " + count);
                        count++;
                        lock.notify();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}