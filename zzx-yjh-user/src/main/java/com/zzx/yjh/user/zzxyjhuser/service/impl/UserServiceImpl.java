package com.zzx.yjh.user.zzxyjhuser.service.impl;

import com.zzx.yjh.user.zzxyjhuser.service.FeignOrderService;
import com.zzx.yjh.user.zzxyjhuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:47
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private FeignOrderService feignOrderService;
    @Override
    public String getUserInfoByUserId(String userId) {
        return "fangjia-user:getUserInfoByUserId run, userId is " + userId;
    }

    @Override
    public String getOrderInfoByOrderId(String orderId) {
        return feignOrderService.getOrderInfoByOrderId(orderId);
    }
}
