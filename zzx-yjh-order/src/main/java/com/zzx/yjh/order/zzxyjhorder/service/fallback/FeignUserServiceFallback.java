package com.zzx.yjh.order.zzxyjhorder.service.fallback;

import com.zzx.yjh.order.zzxyjhorder.service.FeignUserService;
import org.springframework.stereotype.Component;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:33
 **/
@Component
public class FeignUserServiceFallback implements FeignUserService {

    @Override
    public String getUserInfoByUserid(String userId) {
        return "fangjia-order:FeignUserServiceFallback is execute";
    }
}
