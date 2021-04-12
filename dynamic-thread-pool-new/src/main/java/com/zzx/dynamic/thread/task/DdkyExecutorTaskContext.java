package com.zzx.dynamic.thread.task;

import java.util.Map;

/**
 * 叮当线程池任务执行上下文
 * @author zhouzhixiang
 * @Date 2021-04-10
 */
public class DdkyExecutorTaskContext {

    private String taskName;

    private Map<Object, Object> parameters;

    public DdkyExecutorTaskContext(String taskName, Map<Object, Object> parameters) {
        this.taskName = taskName;
        this.parameters = parameters;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Map<Object, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<Object, Object> parameters) {
        this.parameters = parameters;
    }
}
