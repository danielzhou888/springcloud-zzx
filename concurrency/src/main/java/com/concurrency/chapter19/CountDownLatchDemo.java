package com.concurrency.chapter19;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @program: springcloud-zzx
 *
 * @description:
 *
 * @author: zhouzhixiang
 *
 * @create: 2019-05-16 21:06
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        Arrays.asList(new String[]{"加油", "换胎", "换刹车片"}).forEach(i -> {
            new Thread(new Runnable() {
                @Override public void run() {
                    System.out.println("赛车"+i+"进行中。。。。。");
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(7));
                        System.out.println(i+"完毕");
                        TimeUnit.SECONDS.sleep(new Random().nextInt(7));

                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        });

        try {
            countDownLatch.await();
            System.out.println("准备发车");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
