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
public class FutureTaskDemo2 {
    private static final Logger logger = LoggerFactory.getLogger(FutureTaskDemo2.class);
    public static void main(String[] args) throws InterruptedException {
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
                ExecutorService es = Executors.newCachedThreadPool();
                logger.info("老板给我来一个月饼");
                for (int i = 0; i < 3; i++) {
                    FutureTask<Integer> futureTask = new FutureTask<Integer>(productor) {
                        @Override protected void done() {
                            super.done();
                            try {
                                logger.info(String.format(" 编号[%s]月饼已打包好", get()));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    es.submit(futureTask);
                }
            }
        };
        new Thread(customer).start();
    }
}
