package com.zzx.sentinel.client.fallback.handler;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zzx.sentinel.client.fallback.proxy.FallbackProxy;
import com.zzx.sentinel.client.fallback.register.SentinelFallbackBeanRegister;
import com.zzx.sentinel.client.util.FallbackServiceNameUtil;
import org.apache.dubbo.common.utils.ArrayUtils;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流降级统一处理类
 * @author zhouzhixiang
 * @Date 2020-12-01
 */
public class GlobalDubboFallbackHandler {

    private static final Logger logger= LoggerFactory.getLogger(GlobalDubboFallbackHandler.class);

    public static void init() {
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
            return AsyncRpcResult.newDefaultAsyncResult(invocation);
        }
        return result;
    }

    private static Result doFallbackProcess(Invoker<?> invoker, Invocation invocation, BlockException e) {
        String serviceName = invoker.getInterface().getSimpleName();
        String interfaceName = invoker.getInterface().getName();
        String methodName = invocation.getMethodName();
        Class<?>[] parameterTypes = invocation.getParameterTypes();
        // 类:方法(参数)  -》 key，从容器取key对应的FallbackProxy
        ConcurrentHashMap<String, FallbackProxy> fallbackBeanMap = SentinelFallbackBeanRegister.fallbackBeanMap;
        FallbackProxy fallbackProxy = fallbackBeanMap.get(interfaceName);
        if (fallbackProxy != null) {
            String fallbackBeanMethodkey = FallbackServiceNameUtil.getFallbackBeanMethodkey(fallbackProxy.getClass(), methodName, parameterTypes);
            Method proxyMethod = SentinelFallbackBeanRegister.fallbackMethodMap.get(fallbackBeanMethodkey);
            if (proxyMethod != null) {
                try {
                    int modifiers = proxyMethod.getModifiers();
                    if (Modifier.isStatic(modifiers)) {
                        return AsyncRpcResult.newDefaultAsyncResult(proxyMethod.invoke(null, ArrayUtils.isNotEmpty(invocation.getArguments())), invocation);
                    } else {
                        return AsyncRpcResult.newDefaultAsyncResult(proxyMethod.invoke(fallbackProxy, invocation.getArguments()), invocation);
                    }
                } catch (IllegalAccessException ex) {
                    logger.error("doFallbackProcess exception {}", ex);
                } catch (InvocationTargetException ex) {
                    logger.error("doFallbackProcess exception {}", ex);
                }
            }
        }
        return null;
    }

}

