package com.zzx.dynamic.thread.factory;

import com.zzx.dynamic.thread.executor.DdkyExecutor;
import com.zzx.dynamic.thread.executor.NamedThreadFactory;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public class DefaultDdkyExecutorFactoryTest {

    @Test
    public void test() throws InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10),new NamedThreadFactory("DefaultDdkyExecutorFactoryTest.test"));

        CompletableFuture<DdkyExecutor> f1 = CompletableFuture.supplyAsync(() -> {
            DdkyExecutorFactory factory = new DefaultDdkyExecutorFactory();
            DdkyExecutor executor = factory.createCachedExecutor("order-b-executor");
            return executor;
        }, executorService);

        CompletableFuture<DdkyExecutor> f2 = CompletableFuture.supplyAsync(() -> {
            DdkyExecutorFactory factory = new DefaultDdkyExecutorFactory();
            DdkyExecutor executor = factory.createCachedExecutor("order-b-executor");
            return executor;
        }, executorService);

        DdkyExecutor executor1 = f1.join();

        TimeUnit.SECONDS.sleep(2);

        DdkyExecutor executor2 = f2.join();

        int h1 = executor1.hashCode();
        int h2 = executor2.hashCode();

        System.out.println("h1 = "+ h1);
        System.out.println("h2 = "+ h2);

        boolean b = (executor1 == executor2);
        System.out.println("b = "+b);

        executorService.shutdown();
    }
}
