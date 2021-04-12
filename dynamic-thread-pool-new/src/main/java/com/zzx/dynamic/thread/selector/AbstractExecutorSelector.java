package com.zzx.dynamic.thread.selector;

import com.zzx.dynamic.thread.config.DdkyExecutorProperty;
import com.zzx.dynamic.thread.config.DdkyExecutorsProperty;
import com.zzx.dynamic.thread.executor.DdkyExecutor;
import com.zzx.dynamic.thread.task.DdkyExecutorTaskContext;
import com.zzx.dynamic.thread.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhouzhixiang
 * @Date 2021-04-12
 */
public abstract class AbstractExecutorSelector implements ExecutorSelector {

    protected final Logger logger = LoggerFactory.getLogger(AbstractExecutorSelector.class);

    private ExecutorExpression expression;

    private class ExecutorExpression {

        private ExecutorExpressionFunction function;

        private final ConcurrentMap<String, String> cachedInputs = new ConcurrentHashMap<>();

        public ExecutorExpression(ExecutorExpressionFunction function) {
            this.function = function;
        }

        private String match(String input) {
            if (Strings.isBlank(input)) {
                if (logger.isInfoEnabled()) {
                    logger.info("[ExecutorSelector] 表达式：{}，匹配结果：{}", input, DdkyExecutor.DEFAULT_POOL_NAME);
                }
                return DdkyExecutor.DEFAULT_POOL_NAME;
            }
            String poolName = cachedInputs.get(input);
            if (Strings.isNotBlank(poolName)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("[ExecutorSelector] 表达式：{}，匹配结果：{}", input, poolName);
                }
                return poolName;
            }

            if (function == null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("选择器计算引擎function为空，直接返回选择器的结果值：{}", input);
                }
                return input;
            }

            List<DdkyExecutorProperty> propertyList = DdkyExecutorsProperty.getDdkyExecutorPropertyList();
            for (DdkyExecutorProperty property : propertyList) {
                poolName = property.getPoolName();
                String expression = property.getExpression();
                if (Strings.isNotBlank(expression)) {
                    if (function.matches(input, expression)) {
                        cachedInputs.putIfAbsent(input, poolName);
                        if (logger.isDebugEnabled()) {
                            logger.debug("选择器计算引擎匹配到线程池{}，使用此线程池", poolName);
                        }
                        return poolName;
                    }
                }
            }
            if (logger.isDebugEnabled()) {
                logger.debug("选择器计算引擎没有匹配到线程池，将使用默认的线程池：{}", DdkyExecutor.DEFAULT_POOL_NAME);
            }
            return input;
        }

    }

    protected interface ExecutorExpressionFunction {

        /**
         * 输入值是否匹配表达式
         * @param input
         * @param expression
         * @return
         */
        boolean matches(String input, String expression);
    }

    protected AbstractExecutorSelector() {
        expression = new ExecutorExpression(providerFunction());
    }

    /**
     * 表达式计算引擎
     * @return
     */
    protected abstract ExecutorExpressionFunction providerFunction();

    @Override
    public String selectPoolKey(DdkyExecutorTaskContext taskContext) {
        return expression.match(select(taskContext));
    }

    /**
     * 真正的选择逻辑，交给子类去实现
     * @param taskContext
     * @return
     */
    protected abstract String select(DdkyExecutorTaskContext taskContext);
}
