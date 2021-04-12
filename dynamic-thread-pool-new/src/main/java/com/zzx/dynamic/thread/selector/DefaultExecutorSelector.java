package com.zzx.dynamic.thread.selector;

import com.zzx.dynamic.thread.executor.DdkyExecutor;
import com.zzx.dynamic.thread.task.DdkyExecutorTaskContext;

/**
 * 默认线程池选择器，直接选择默认线程池
 * @author zhouzhixiang
 * @Date 2021-04-09
 */
public class DefaultExecutorSelector extends AbstractExecutorSelector {
    @Override
    protected ExecutorExpressionFunction providerFunction() {
        return null;
    }

    @Override
    protected String select(DdkyExecutorTaskContext taskContext) {
        if (logger.isDebugEnabled()) {
            logger.debug("[DefaultExecutorSelector] 使用默认线程池选择器，返回默认的线程池{}", DdkyExecutor.DEFAULT_POOL_NAME);
        }
        return DdkyExecutor.DEFAULT_POOL_NAME;
    }
}
