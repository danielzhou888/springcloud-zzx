package com.zzx.dynamic.thread.task;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public class DefaultExecutorTask<V> implements DdkyExecutorTask<V> {

    private final DdkyExecutorTaskContext taskContext;

    private final Callable<V> actualTask;

    private final AtomicReference<TaskState> state;

    public DefaultExecutorTask(DdkyExecutorTaskContext taskContext, Callable<V> actualTask) {
        this.taskContext = taskContext;
        this.actualTask = actualTask;
        this.state = new AtomicReference<>(TaskState.CREATED);
    }

    @Override
    public DdkyExecutorTaskContext getTaskContext() {
        return taskContext;
    }

    @Override
    public void changeState(TaskState newTaskState) {
        state.set(newTaskState);

        switch (newTaskState) {
            case CREATED:
            case COMMITTED:
            case REJECTED:
            case RUNNING:
            case SUCCESS:
            case FAILURE:
            case COMPLETED:
            default:
        }
    }

    @Override
    public void destroy() {
        changeState(TaskState.COMMITTED);
    }

    @Override
    public V call() throws Exception {
        return actualTask.call();
    }
}
