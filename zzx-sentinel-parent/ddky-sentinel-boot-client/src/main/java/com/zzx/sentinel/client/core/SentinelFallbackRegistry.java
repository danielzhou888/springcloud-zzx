package com.zzx.sentinel.client.core;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * Sentinel Fallback初始化注册器
 * @author zhouzhixiang
 * @Date 2020-12-24
 */
public class SentinelFallbackRegistry implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        registerFallbackBean(contextRefreshedEvent.getApplicationContext());
    }

    private void registerFallbackBean(ApplicationContext applicationContext) {
        Map<String, FallbackProxy> fallbackProxyMap = applicationContext.getBeansOfType(FallbackProxy.class);
        if (CollectionUtils.isEmpty(fallbackProxyMap) && applicationContext.getParent() != null) {
            registerFallbackBean(applicationContext.getParent());
        }
        fallbackProxyMap.entrySet().forEach(entry -> {
            if (entry.getValue() instanceof FallbackProxy) {
                SentinelFallbackBeanFactory.fallbackBeanMap.putIfAbsent(entry.getKey(), entry.getValue());
            }
        });
    }
}
