package com.zzx.kafka.producer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * 添加静态资源访问
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/3rd/**").addResourceLocations("classpath:/WEB-INF/3rd/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/WEB-INF/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/WEB-INF/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/WEB-INF/css/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/index.html");
    }

}
