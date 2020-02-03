package com.zzx.yjh.user.zzxyjhuser.service.fallback;

import com.zzx.yjh.user.zzxyjhuser.service.FeignOrderService;
import org.springframework.stereotype.Component;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
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
