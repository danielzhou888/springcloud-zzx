package com.zzx.druid.controller;

import com.zzx.druid.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
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
