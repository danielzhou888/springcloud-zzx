package com.zzx.sentinel.order.sentinel;


/**
 * 订单API：限流降级处理逻辑
 * @author zhouzhixiang
 * @Date 2020-12-06
 */
public class VoucherApiSentinel {

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
}
