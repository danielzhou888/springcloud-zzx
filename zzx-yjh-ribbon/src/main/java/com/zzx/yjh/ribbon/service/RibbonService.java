package com.zzx.yjh.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-04 23:54
 **/
@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;

    public String hello() {
        return restTemplate.getForObject("http://FANGJIA-EUREKA-CLIENT-SLAVE1/eurekaClient/slave1/hello", String.class);
    }
}

