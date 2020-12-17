package com.zzx.sentinel.product.config;


import com.zzx.sentinel.client.annotation.aspectj.SentinelAnnotationAspect;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Eric Zhao
 */
@Configuration
@DubboComponentScan
public class AopConfiguration {

    @Bean
    public SentinelAnnotationAspect sentinelResourceAspect() {
        return new SentinelAnnotationAspect();
    }
}