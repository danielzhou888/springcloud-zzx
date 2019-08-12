package com.zzx.yjh.user.zzxyjhuser.service;

import com.zzx.yjh.user.zzxyjhuser.service.fallback.FeignOrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:49
 **/
@FeignClient(name = "fangjia-order", fallback = FeignOrderServiceFallback.class)
public interface FeignOrderService {

    @GetMapping("/order/getOrderInfoByOrderId")
    String getOrderInfoByOrderId(@RequestParam("orderId") String orderId);
}
