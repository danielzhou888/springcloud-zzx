package com.zzx.yjh.user.zzxyjhuser.controller;

import com.zzx.yjh.user.zzxyjhuser.service.FeignOrderService;
import com.zzx.yjh.user.zzxyjhuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:45
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private FeignOrderService feignOrderService;

    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfoByUserId")
    public String getUserInfoByUserId(@RequestParam("userId") String userId) {
        return userService.getUserInfoByUserId(userId);
    }

    @GetMapping("/getOrderInfoByOrderId")
    public String getOrderInfoByOrderId(@RequestParam("orderId") String orderId) {
        return feignOrderService.getOrderInfoByOrderId(orderId);
    }
}
