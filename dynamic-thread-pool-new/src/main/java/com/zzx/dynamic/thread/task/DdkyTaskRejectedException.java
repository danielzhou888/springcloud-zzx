package com.zzx.dynamic.thread.task;

import java.util.concurrent.RejectedExecutionException;

/**
 * 任务提交被拒绝异常
 * @author zhouzhixiang
 * @Date 2021-04-10
 */
public class DdkyTaskRejectedException extends RejectedExecutionException {

    private RejectedExecutionException executionException;

    public DdkyTaskRejectedException() {}

    public DdkyTaskRejectedException(RejectedExecutionException executionException) {
        super(executionException);
        this.executionException = executionException;
    }

    public RejectedExecutionException getRejectedExecutionException() {
        return executionException;
    }

    public boolean existRejectedExecutionEception() {
        return executionException != null;
    }
}
