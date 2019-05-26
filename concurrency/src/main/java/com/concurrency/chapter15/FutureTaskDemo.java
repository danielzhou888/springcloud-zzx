package com.concurrency.chapter15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @program: springcloud-zzx
 *
 * @description:
 *
 * @author: zhouzhixiang
 *
 * @create: 2019-05-14 19:58
 */
public class FutureTaskDemo {
    private static final Logger logger = LoggerFactory.getLogger(FutureTaskDemo.class);
    public static void main(String[] args) {
        // 月饼生产者
        final Callable<Integer> productor = new Callable<Integer>() {
            @Override public Integer call() throws Exception {
                logger.info("月饼制作中。。。。");
                Thread.sleep(5000);
                return (Integer)new Random().nextInt(1000);
            }
        };

        // 月饼消费者
        Runnable customer = new Runnable() {
            @Override public void run() {
                ExecutorService es = Executors.newSingleThreadExecutor();
                logger.info("老板给我来一个月饼");
                FutureTask<Integer> futureTask = new FutureTask<>(productor);
                es.submit(futureTask);
                try {
                    logger.info(String.format(" 编号[%s]月饼已打包好", futureTask.get()));
                    logger.info("回家。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(customer).start();
    }
}
