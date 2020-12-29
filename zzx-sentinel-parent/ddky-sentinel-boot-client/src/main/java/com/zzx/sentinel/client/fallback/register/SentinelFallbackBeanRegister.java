package com.zzx.sentinel.client.fallback.register;

import com.zzx.sentinel.client.fallback.proxy.FallbackProxy;
import com.zzx.sentinel.client.util.FallbackServiceNameUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Sentinel Fallback Bean工厂
 * 用于获取Fallback 代理Bean
 * @author zhouzhixiang
 * @Date 2020-12-24
 */
public class SentinelFallbackBeanRegister {
    public static final ConcurrentHashMap<String /** Fallback Bean Name */, FallbackProxy> fallbackBeanMap = new ConcurrentHashMap(16);
    public static final ConcurrentHashMap<String/** 类:方法(参数) */, Method /** method对象 */> fallbackMethodMap = new ConcurrentHashMap(16);


    protected void registerFalllbackBeanMethod() {
        if (!CollectionUtils.isEmpty(SentinelFallbackBeanRegister.fallbackBeanMap)) {
            SentinelFallbackBeanRegister.fallbackBeanMap.values().forEach(fallbackProxy -> {
                Method[] methods = fallbackProxy.getClass().getDeclaredMethods();
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        String fallbackBeanMethodkey = FallbackServiceNameUtil.getFallbackBeanMethodkey(fallbackProxy.getClass(), method.getName(), parameterTypes);
                        fallbackMethodMap.putIfAbsent(fallbackBeanMethodkey, method);
                    }
                }
            });
        }
    }

    protected void registerFallbackBean(ApplicationContext applicationContext) {
        Map<String, FallbackProxy> fallbackProxyMap = applicationContext.getBeansOfType(FallbackProxy.class);
        if (CollectionUtils.isEmpty(fallbackProxyMap) && applicationContext.getParent() != null) {
            registerFallbackBean(applicationContext.getParent());
        }
        fallbackProxyMap.entrySet().forEach(entry -> {
            if (entry.getValue() instanceof FallbackProxy) {
                FallbackProxy value = entry.getValue();
                Type[] genericInterfaces = value.getClass().getGenericInterfaces();
                ParameterizedType parameterizedTypes = (ParameterizedType) genericInterfaces[0];
                Type actualTypeArgument = parameterizedTypes.getActualTypeArguments()[0];
                Class<?> clazz = (Class<?>)actualTypeArgument;
                fallbackBeanMap.putIfAbsent(clazz.getName(), entry.getValue());
            }
        });
    }

}
