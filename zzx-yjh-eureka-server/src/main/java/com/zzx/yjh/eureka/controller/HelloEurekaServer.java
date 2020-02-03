package com.zzx.yjh.eureka.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-04 22:15
 **/
@RestController
@RequestMapping("/eurekaServer")
public class HelloEurekaServer {

    @GetMapping("/hello")
    @ApiOperation(value = "hello demo", notes = "just demo")
    public String hello() {
        return "hello eurekaServer";
    }
}
