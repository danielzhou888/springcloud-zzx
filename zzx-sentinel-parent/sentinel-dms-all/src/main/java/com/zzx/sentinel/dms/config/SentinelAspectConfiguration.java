package com.zzx.sentinel.dms.config;

import com.zzx.sentinel.client.annotation.aspectj.SentinelAnnotationAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouzhixiang
 * @Date 2020-09-25
 */
@Configuration
public class SentinelAspectConfiguration {

    @Bean
    public SentinelAnnotationAspect sentinelResourceAspect() {
        return new SentinelAnnotationAspect();
    }
}
