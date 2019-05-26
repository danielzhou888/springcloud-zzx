package com.zhouzhixiang.serviceribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-03-25 10:44
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;


    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name, String.class);
    }
}
