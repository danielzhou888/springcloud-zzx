package com.zhouzhixiang.servicehi.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sc-f-chapter1
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-03-21 19:17
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(@RequestParam(value = "name", defaultValue = "zhouzhixiang") String name) {
        return "hi " + name + " , I am from port: " + port;
    }

    public String hiError(String name) {
        return "hi, "+name+", sorry, error!";
    }

}
