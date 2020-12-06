package com.zzx.sentinel.orderweb.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
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