package com.concurrency.chapter18;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: springcloud-zzx
 *
 * @description:
 *
 * @author: zhouzhixiang
 *
 * @create: 2019-05-16 20:16
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        Arrays.asList(new String[]{"中国车队", "法国车队","美国车队", "英国车队", "日本车队"})
            .forEach(i -> {
                new Thread(new Runnable() {
                    @Override public void run() {
                        try {
                            Thread.sleep(new Random().nextInt(6));
                            System.out.println("车队共"+cyclicBarrier.getParties()+"组，" + i + "第"+cyclicBarrier.getNumberWaiting()+"个到达赛场，准备开始比赛");
                            cyclicBarrier.await();
                            System.out.println(i+"准备触发");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            });

    }
}
