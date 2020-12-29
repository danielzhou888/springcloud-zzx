package com.zzx.sentinel.client.init;


import com.zzx.sentinel.client.fallback.handler.GlobalDubboFallbackHandler;
import com.zzx.sentinel.client.fallback.register.SentinelFallbackBeanRegister;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Sentinel Fallback初始化注册器
 * @author zhouzhixiang
 * @Date 2020-12-24
 */
public class SentinelFallbackIniter extends SentinelFallbackBeanRegister implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        registerFallbackBean(contextRefreshedEvent.getApplicationContext());
        registerFalllbackBeanMethod();
        GlobalDubboFallbackHandler.init();
    }


}
