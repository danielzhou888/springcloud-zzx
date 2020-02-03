package com.zzx.yjh.eureka.client.slaveone.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-04 22:15
 **/
@RestController
@RequestMapping("/eurekaClient/slave1")
public class HelloEurekaClient {

    @Value("${server.port}")
    String port;

    @GetMapping("/hello")
    @ApiOperation(value = "hello demo", notes = "just demo")
    public String hello(@RequestParam(value = "name") String name) {
        return "hello eurekaClient slave1, name is "+name+", port: " + port;
    }
}
