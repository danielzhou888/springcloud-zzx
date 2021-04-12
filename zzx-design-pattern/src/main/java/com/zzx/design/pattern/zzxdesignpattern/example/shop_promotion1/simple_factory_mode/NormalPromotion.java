package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.simple_factory_mode;

/**
 * 无促销，正常价格
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
public class NormalPromotion implements PromotionSuper {
    @Override
    public double calcutePrice(double money) {
        return money;
    }
}
