package com.zzx.yjh.user.zzxyjhuser.service;

import com.zzx.yjh.user.zzxyjhuser.service.fallback.FeignOrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:49
 **/
@FeignClient(name = "fangjia-order", fallback = FeignOrderServiceFallback.class)
public interface FeignOrderService {

    @GetMapping("/order/getOrderInfoByOrderId")
    String getOrderInfoByOrderId(@RequestParam("orderId") String orderId);
}
