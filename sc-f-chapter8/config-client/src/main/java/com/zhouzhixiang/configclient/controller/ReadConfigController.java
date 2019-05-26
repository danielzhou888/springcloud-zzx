package com.zhouzhixiang.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-03-25 17:25
 */
@RestController
@RefreshScope
public class ReadConfigController {

    @Value("${foo}")
    String foo;

    @Value("${test}")
    String test;

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public String hi() {
        return foo;
    }

    @RequestMapping(value = "hi2", method = RequestMethod.GET)
    public String hi2() {
        return test;
    }



}
