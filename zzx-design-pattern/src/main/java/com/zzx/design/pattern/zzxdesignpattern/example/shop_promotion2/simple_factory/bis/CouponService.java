package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory.bis;

/**
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
public class CouponService {

    public CouponResult sendCoupon(String uId, String commodityId, String bizId) throws Exception {
        return new CouponResult();
    }
}
