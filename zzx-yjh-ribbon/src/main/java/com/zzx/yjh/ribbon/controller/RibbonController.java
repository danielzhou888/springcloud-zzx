package com.zzx.yjh.ribbon.controller;

import com.zzx.yjh.ribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-04 23:53
 **/
@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @Autowired
    RibbonService ribbonService;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name) {
        return ribbonService.hello(name);
    }
}
