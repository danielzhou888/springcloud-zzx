package com.zzx.druid.controller;

import com.zzx.druid.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-21 16:31
 **/
@RestController
@Controller
public class TestController {
    @Autowired
    private ITestService testService;

    @RequestMapping("/test")
    public String greeting() {
        testService.test();
        return "hello";
    }

    @RequestMapping("/transaction")
    public String transaction() {
        try {
            testService.testTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "transaction";
    }
}
