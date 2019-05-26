package com.zhouzhixiang.servicehi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-03-25 10:57
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    String port;

    @Autowi

    @GetMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "zhouzhixiang") String name) {
        return "hi" + name + " , I am from port: " + port;
    }

}
