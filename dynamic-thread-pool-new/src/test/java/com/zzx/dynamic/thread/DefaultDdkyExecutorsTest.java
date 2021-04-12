package com.zzx.dynamic.thread;

import com.zzx.dynamic.thread.task.DdkyExecutorTaskContext;
import com.zzx.dynamic.thread.task.DefaultExecutorTask;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public class DefaultDdkyExecutorsTest {

    @Test
    public void test() throws InterruptedException {
        DdkyExecutors executors = new DefaultDdkyExecutors();
        Callable<Void> task = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                    System.out.println("1111111222222");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        };
        Runnable submitTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        executors.submit(new DefaultExecutorTask<>(new DdkyExecutorTaskContext(i + "", new HashMap<>()), task));
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        new Thread(submitTask).start();
        new Thread(submitTask).start();
        new Thread(submitTask).start();

        TimeUnit.SECONDS.sleep(10);

        executors.shutdown(5, TimeUnit.SECONDS);
    }
}
