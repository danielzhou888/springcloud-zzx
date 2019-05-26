package com.zhouzhixiang.servicefeign.controller;

import com.zhouzhixiang.servicefeign.clients.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-03-25 11:44
 */
@RestController
public class HelloController {

    @Autowired
    SchedualServiceHi schedualServiceHi;

    @GetMapping("/hi")
    public String hi(@RequestParam(name = "name", defaultValue = "zhouzhixiang") String name) {
        return schedualServiceHi.sayHiFromClientOne(name);
    }

}
