package com.zzx.design.pattern.zzxdesignpattern.example.redis_egm_iir.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author zhouzhixiang
 * @Date 2020-12-01
 */
public class JDKProxy {

    public static <T> T getProxy(Class<T> interfaceClass, ICacheAdapter cacheAdapter) throws Exception {
        InvocationHandler invocationHandler = new JDKInvocationHandler(cacheAdapter);
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = interfaceClass.getInterfaces();
        return (T) Proxy.newProxyInstance(contextClassLoader, new Class[]{classes[0]}, invocationHandler);
    }
}
