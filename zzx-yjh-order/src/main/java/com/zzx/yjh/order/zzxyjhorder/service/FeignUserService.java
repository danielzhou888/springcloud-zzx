package com.zzx.yjh.order.zzxyjhorder.service;

import com.zzx.yjh.order.zzxyjhorder.service.fallback.FeignUserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:32
 **/
@FeignClient(name = "fangjia-user", fallback = FeignUserServiceFallback.class)
public interface FeignUserService {

    @GetMapping("/user/getUserInfoByUserId")
    String getUserInfoByUserid(@RequestParam("userId") String userId);
}
