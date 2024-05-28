package com.jeek.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class CommandHelloWorld extends HystrixCommand<String> {

    protected CommandHelloWorld(HystrixCommandGroupKey group) {
        super(group);
    }

    protected CommandHelloWorld(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool) {
        super(group, threadPool);
    }

    protected CommandHelloWorld(HystrixCommandGroupKey group, int executionIsolationThreadTimeoutInMilliseconds) {
        super(group, executionIsolationThreadTimeoutInMilliseconds);
    }

    protected CommandHelloWorld(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool, int executionIsolationThreadTimeoutInMilliseconds) {
        super(group, threadPool, executionIsolationThreadTimeoutInMilliseconds);
    }

    protected CommandHelloWorld(Setter setter) {
        super(setter);
    }

    @Override
    protected String run() throws Exception {
        return null;
    }
}
