package com.zzx.yjh.order.zzxyjhorder.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zzx.yjh.order.zzxyjhorder.service.FeignUserService;
import com.zzx.yjh.order.zzxyjhorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:12
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private FeignUserService feignUserService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/getUserInfoByUserId")
    public String getUserInfoById(@RequestParam("userId") String userId) {
        return orderService.getUserByUserId(userId);
    }

    @GetMapping("/getOrderInfoByOrderId")
    public String getOrderInfoByOrderId(@RequestParam("orderId") String orderId) {
        return orderService.getOrderByOrderid(orderId);
    }
}