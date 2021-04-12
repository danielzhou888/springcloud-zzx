package com.zzx.kafka.producer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: 20160219
 * @description: 网易云信配置文件
 * @author: zhouzhixiang
 * @date: 2019-12-09
 * @company: 叮当快药科技集团有限公司
 **/
@Component
@PropertySource("classpath:application.properties")
@Data
public class ImNimConfig {

    @Value("${nim.app.key}")
    private String appkey;
    @Value("${nim.app.secret}")
    private String appSecret;
    @Value("${nim.message.topic}")
    private String MessageTopic;

}
