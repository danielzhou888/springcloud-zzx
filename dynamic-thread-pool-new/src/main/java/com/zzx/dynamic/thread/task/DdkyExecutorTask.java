package com.zzx.dynamic.thread.task;

import java.util.concurrent.Callable;

/**
 * 叮当线程池任务
 * @author zhouzhixiang
 * @Date 2021-04-10
 */
public interface DdkyExecutorTask<V> extends Callable<V> {

    /**
     * 获取任务执行上下文
     * @return
     */
    DdkyExecutorTaskContext getTaskContext();

    void changeState(TaskState newTaskState);

    void destroy();

    enum TaskState {
        CREATED,
        REJECTED,
        COMMITTED,
        RUNNING,
        SUCCESS,
        FAILURE,
        COMPLETED
    }
}
