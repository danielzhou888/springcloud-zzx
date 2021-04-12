package com.zzx.dynamic.thread.selector;

import com.zzx.dynamic.thread.task.DdkyExecutorTaskContext;

/**
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public interface ExecutorSelector {

    /**
     * 根据上下文选择器线程池
     * @param taskContext
     * @return
     */
    String selectPoolKey(DdkyExecutorTaskContext taskContext);
}
