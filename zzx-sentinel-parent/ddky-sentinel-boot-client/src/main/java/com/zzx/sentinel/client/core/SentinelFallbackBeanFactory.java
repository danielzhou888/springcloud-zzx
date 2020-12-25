package com.zzx.sentinel.client.core;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Sentinel Fallback Bean工厂
 * 用于获取Fallback 代理Bean
 * @author zhouzhixiang
 * @Date 2020-12-24
 */
public class SentinelFallbackBeanFactory {
    public static ConcurrentHashMap<String, FallbackProxy> fallbackBeanMap = new ConcurrentHashMap(16);
}
