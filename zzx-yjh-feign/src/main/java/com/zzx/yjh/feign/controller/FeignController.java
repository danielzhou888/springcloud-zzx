package com.zzx.yjh.feign.controller;

import com.zzx.yjh.feign.service.IFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    IFeignService iFeignService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name) {
        return iFeignService.hello(name);
    }
}
