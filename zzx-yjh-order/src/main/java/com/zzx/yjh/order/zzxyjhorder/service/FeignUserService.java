package com.zzx.yjh.order.zzxyjhorder.service;

import com.zzx.yjh.order.zzxyjhorder.service.fallback.FeignUserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:32
 **/
@FeignClient(name = "fangjia-user", fallback = FeignUserServiceFallback.class)
public interface FeignUserService {

    @GetMapping("/user/getUserInfoByUserId")
    String getUserInfoByUserid(@RequestParam("userId") String userId);
}
