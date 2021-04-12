package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory;

/**
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
public class StoreFactory {

    public ICommodity getICommodity(Integer type) {
        if (type == 1) {
            return new CouponCommodityService();
        } else if (type == 2) {
            return new GoodsCommodityService();
        } else if (type == 3) {
            return new CardCommodityService();
        }
        return null;
    }
}
