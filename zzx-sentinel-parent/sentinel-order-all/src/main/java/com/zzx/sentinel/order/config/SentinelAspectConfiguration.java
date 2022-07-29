package com.zzx.sentinel.order.config;

import com.zzx.sentinel.client.annotation.aspectj.SentinelAnnotationAspect;
import com.zzx.sentinel.client.init.SentinelFallbackIniter;
import com.zzx.sentinel.client.fallback.handler.GlobalDubboFallbackHandler;
import com.zzx.sentinel.order.fallback.VoucherApiFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouzhixiang
 * @Date 2020-09-25
 */
@Configuration
public class SentinelAspectConfiguration {

    //@Bean
    //public SentinelResourceAspect sentinelResourceAspect() {
    //    return new SentinelResourceAspect();
    //}


    @Bean
    public SentinelAnnotationAspect sentinelAnnotationAspect() {
        return new SentinelAnnotationAspect();
    }

    @Bean
    public GlobalDubboFallbackHandler globalDubboFallbackHandler() {
        return new GlobalDubboFallbackHandler();
    }

    @Bean
    public VoucherApiFallback voucherApiSentinel() {
        return new VoucherApiFallback();
    }

    @Bean
    public SentinelFallbackIniter sentinelFallbackRegistry() {
        return new SentinelFallbackIniter();
    }

}
