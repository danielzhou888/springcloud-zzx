package com.zzx.yjh.user.zzxyjhuser.service.fallback;

import com.zzx.yjh.user.zzxyjhuser.service.FeignOrderService;
import org.springframework.stereotype.Component;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:50
 **/
@Component
public class FeignOrderServiceFallback implements FeignOrderService {
    @Override
    public String getOrderInfoByOrderId(String orderId) {
        return "fangjia-user:FeignOrderServiceFallback run, orderId is " + orderId;
    }
}
