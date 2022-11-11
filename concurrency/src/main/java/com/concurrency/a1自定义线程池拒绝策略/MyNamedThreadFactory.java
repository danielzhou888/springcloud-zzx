package com.concurrency.a1自定义线程池拒绝策略;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class MyNamedThreadFactory implements ThreadFactory {

	    protected final AtomicInteger mThreadNum = new AtomicInteger(1);

	    protected final String mPrefix;

	    protected final boolean mDaemon;

	    protected final ThreadGroup mGroup;

	    public MyNamedThreadFactory(String prefix) {
	        this(prefix, false);
	    }

	    public MyNamedThreadFactory(String prefix, boolean daemon) {
	        mPrefix = prefix + "-thread-";
	        mDaemon = daemon;
	        SecurityManager s = System.getSecurityManager();
	        mGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
	    }

	    @Override
	    public Thread newThread(Runnable runnable) {
	        String name = mPrefix + mThreadNum.getAndIncrement();
	        Thread ret = new Thread(mGroup, runnable, name, 0);
	        ret.setDaemon(mDaemon);
	        return ret;
	    }
	}