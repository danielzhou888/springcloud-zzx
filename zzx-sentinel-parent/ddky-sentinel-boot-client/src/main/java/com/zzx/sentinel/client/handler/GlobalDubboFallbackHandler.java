package com.zzx.sentinel.client.handler;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zzx.sentinel.client.core.FallbackProxy;
import com.zzx.sentinel.client.core.SentinelFallbackBeanFactory;
import com.zzx.sentinel.client.util.FallbackResultUtil;
import com.zzx.sentinel.client.util.FallbackServiceNameUtil;
import org.apache.dubbo.common.utils.ArrayUtils;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 限流降级统一处理类
 * @author zhouzhixiang
 * @Date 2020-12-01
 */
public class GlobalDubboFallbackHandler {

    private static final Logger logger= LoggerFactory.getLogger(GlobalDubboFallbackHandler.class);

    static {
        DubboFallbackRegistry.setProviderFallback(new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException e) {
                return doHandle(invoker, invocation, e);
            }
        });

        DubboFallbackRegistry.setConsumerFallback(new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException e) {
                return doHandle(invoker, invocation, e);
            }
        });
    }

    private static Result doHandle(Invoker<?> invoker, Invocation invocation, BlockException e) {
        Result result = doFallbackProcess(invoker, invocation, e);
        if (result == null) {
            RpcInvocation rpcInvocation = (RpcInvocation) invocation;
            Class<?> returnType = rpcInvocation.getReturnType();
            result = FallbackResultUtil.getEmptyResult(returnType, invocation);
        }
        return result;
    }

    private static Result doFallbackProcess(Invoker<?> invoker, Invocation invocation, BlockException e) {
        String serviceName = invoker.getInterface().getSimpleName();
        String methodName = invocation.getMethodName();
        Class<?>[] parameterTypes = invocation.getParameterTypes();

        FallbackProxy fallbackProxy = SentinelFallbackBeanFactory.fallbackBeanMap.get(FallbackServiceNameUtil.getFallbackServiceName(serviceName));
        if (fallbackProxy != null) {
            Class<?> targetServiceClz = fallbackProxy.getClass();
            try {
                Method method = targetServiceClz.getMethod(methodName, parameterTypes);
                if (method != null) {
                    int modifiers = method.getModifiers();

                    if (Modifier.isStatic(modifiers)) {
                        return AsyncRpcResult.newDefaultAsyncResult(method.invoke(null, ArrayUtils.isNotEmpty(invocation.getArguments())), invocation);
                    } else {
                        return AsyncRpcResult.newDefaultAsyncResult(method.invoke(fallbackProxy, invocation.getArguments()), invocation);
                    }
                }
            } catch (NoSuchMethodException ex) {
                logger.error("doFallbackProcess exception {}", ex);
            } catch (IllegalAccessException ex) {
                logger.error("doFallbackProcess exception {}", ex);
            } catch (InvocationTargetException ex) {
                logger.error("doFallbackProcess exception {}", ex);
            }
        }
        return null;
    }

}
