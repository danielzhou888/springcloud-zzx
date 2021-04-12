package com.zzx.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: kafka topic配置类
 * @author: zhouzhixiang
 * @date: 2019-12-12
 * @company: 叮当快药科技集团有限公司
 **/
@Configuration
@EnableConfigurationProperties(KafkaTopicProperties.class)
public class KafkaTopicConfiguration {
    @Autowired
    private KafkaTopicProperties properties;

    @Bean
    public String topicGroupId() {
        return properties.getGroupId();
    }

    @Bean
    public String[] topicName() {
        return properties.getTopicName();
    }
}
