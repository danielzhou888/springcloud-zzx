package com.concurrency.chapter20;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: springcloud-zzx
 *
 * @description:
 *
 * @author: zhouzhixiang
 *
 * @create: 2019-05-16 21:19
 */
public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException {
        final Exchanger<Integer> exchanger = new Exchanger<Integer>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override public void run() {
                int result = 70 * 50;
                System.out.println(Thread.currentThread().getName() + "计算70 * 50结果是：" + result);
                try {
                    exchanger.exchange(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(1000);

        executorService.submit(new Runnable() {
            @Override public void run() {
                int result = 0;
                for (int i = 0; i < 70; i++) {
                    result += 50;
                }
                System.out.println(Thread.currentThread().getName() + "计算70 * 50结果是：" + result);
                try {
                    Integer result_fromOtherThread = exchanger.exchange(result);
                    if (result_fromOtherThread == result) {
                        System.out.println("计算结果相同");
                    } else {
                        System.out.println("计算结果不同");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
