package com.zzx.yjh.order.zzxyjhorder.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zzx.yjh.order.zzxyjhorder.service.FeignUserService;
import com.zzx.yjh.order.zzxyjhorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:14
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    FeignUserService feignUserService;

    @Override
    public String getOrderByOrderid(String orderId) {
        return "fangjia-order:getOrderByOrderid run, orderId is " + orderId;
    }

//    @HystrixCommand(fallbackMethod = "orderFallback")
    @Override
    public String getUserByUserId(String userId) {
        return feignUserService.getUserInfoByUserid(userId);
    }

    public String orderFallback(String userId) {
        return "fangjia-order:orderFallback run, userId is " + userId;
    }

}
