package com.zzx.sentinel.orderweb.config;

import com.zzx.sentinel.client.annotation.aspectj.SentinelAnnotationAspect;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Sentinel 配置
 * @author zhouzhixiang
 */
@Configuration
@DubboComponentScan
//@ConfigurationProperties
public class SentinelConfiguration {

    //@Bean
    //public SentinelResourceAspect sentinelResourceAspect() {
    //    return new SentinelResourceAspect();
    //}


    @Bean
    public SentinelAnnotationAspect sentinelAnnotationAspect() {
        return new SentinelAnnotationAspect();
    }

    //@Bean
    //public FilterRegistrationBean sentinelFilterRegistration() {
    //    FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
    //    registration.setFilter(new CommonFilter());
    //    registration.addUrlPatterns("/*");
    //    registration.setName("sentinelFilter");
    //    registration.setOrder(1);
    //    return registration;
    //}
}