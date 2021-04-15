package com.zzx.dynamic.thread.executor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 * @author zhouzhixiang
 * @Date 2021-04-09
 */
public class NamedThreadFactory extends AtomicInteger implements ThreadFactory {

    private final String prefix;

    private final boolean daemon;

    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(String prefix, boolean daemon) {
        this.prefix = prefix;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, prefix + "-" + incrementAndGet());
        thread.setDaemon(daemon);
        return thread;
    }
}
