package com.zzx.dynamic.thread.executor;

import com.zzx.dynamic.thread.queue.BlockingQueueBuilder;
import com.zzx.dynamic.thread.queue.ResizableLinkedBlockingQueue;
import com.zzx.dynamic.thread.task.DdkyExecutorTaskContext;
import com.zzx.dynamic.thread.task.DefaultExecutorTask;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouzhixiang
 * @Date 2021-04-12
 */

public class DefaultDdkyThreadPoolExecutorTest {

    @Test
    public void test() throws InterruptedException {

        DdkyExecutor executor = DefaultDdkyThreadPoolExecutor.newBuilder()
                .poolName("test-ddky-pool")
                .corePoolSize(3)
                .maximumPoolSize(5)
                .keepAliveTime(10000)
                .workQueue(new BlockingQueueBuilder<Runnable>()
                        .capacity(10)
                        .type(ResizableLinkedBlockingQueue.class.getSimpleName())
                        .fair(false)
                        .build())
                .rejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy())
                .builder();
        printExecutorStatus(executor, "修改之前：");

        Callable<Void> task = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                    printExecutorStatus(executor, "创建任务：");
                    TimeUnit.MILLISECONDS.sleep(1);
                    executor.setCorePoolSize(12);
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
                        executor.submit(new DefaultExecutorTask<>(new DdkyExecutorTaskContext(i + "", new HashMap<>()), task));
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(submitTask).start();
        new Thread(submitTask).start();
        new Thread(submitTask).start();

        TimeUnit.SECONDS.sleep(10);

        printExecutorStatus(executor, "结束：");
        executor.shutdown(5, TimeUnit.SECONDS);
    }

    private static void printExecutorStatus(DdkyExecutor executor, String prefix) {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix)
                .append("poolName: ").append(executor.getPoolName()).append(" ")
                .append("coreSize: ").append(executor.getCorePoolSize()).append(" ")
                .append("maximumPoolSize: ").append(executor.getMaximumPoolSize()).append(" ")
                .append("activeCount: ").append(executor.getActiveCount()).append(" ")
                .append("poolSize: ").append(executor.getPoolSize()).append(" ")
                .append("largestPoolSize: ").append(executor.getLargestPoolSize()).append(" ")
                .append("queueType: ").append(executor.getWorkQueueType()).append(" ")
                .append("queueCapacity: ").append(executor.getWorkQueueCapacity()).append(" ")
                .append("queueSize: ").append(executor.getWorkQueueSize()).append(" ")
                .append("queueRemainingCapacity: ").append(executor.getCompletedTaskCount()).append(" ")
                .append("rejectCount: ").append(executor.getRejectedTaskCount());
        System.out.println(builder.toString());
    }
}
