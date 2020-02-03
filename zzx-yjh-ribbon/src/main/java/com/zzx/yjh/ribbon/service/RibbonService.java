package com.zzx.yjh.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-04 23:54
 **/
@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String hello(String name) {
        return restTemplate.getForObject("http://FANGJIA-EUREKA-CLIENT-SLAVE1/eurekaClient/slave1/hello?name="+name, String.class);
    }

    public String helloFallBack(String name) {
        return name + " hello sorry! From fallback.";
    }
}

