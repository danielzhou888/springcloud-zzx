package com.zzx.yjh.ribbon.controller;

import com.zzx.yjh.ribbon.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
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
