package com.zzx.sentinel.order.fallback;


import com.zzx.sentinel.client.fallback.proxy.FallbackProxy;
import com.zzx.sentinel.voucher.api.VoucherApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单API：限流降级处理逻辑
 * @author zhouzhixiang
 * @Date 2020-12-06
 */
@Slf4j
public class VoucherApiFallback implements FallbackProxy<VoucherApi> {

    @Autowired
    private VoucherApi voucherApi;

    /**
     * 获取会员折扣：降级逻辑
     * @param userId
     * @return
     */
    public static Double getUserRankDiscount(Long userId) {
        return 1d;
    }

    public static boolean executePromotionLocal(Long userId, String orderCode) {
        return true;
    }

    public List testGlobalFallbackHandler(int type, String name) {
        List list = new ArrayList();
        list.add("order degrade .... ");
        return list;
    }
}
