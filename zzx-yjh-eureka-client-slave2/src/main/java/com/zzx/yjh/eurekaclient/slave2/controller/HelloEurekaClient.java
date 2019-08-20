package com.zzx.yjh.eurekaclient.slave2.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-04 22:15
 **/
@RestController
@RequestMapping("/eurekaClient/slave2")
public class HelloEurekaClient {

    @GetMapping("/hello")
    @ApiOperation(value = "hello demo", notes = "just demo")
    public String hello() {
        return "hello eurekaClient slave2";
    }
}
