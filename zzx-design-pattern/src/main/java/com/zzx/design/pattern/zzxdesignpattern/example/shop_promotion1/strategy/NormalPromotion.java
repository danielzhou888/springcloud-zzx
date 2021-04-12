package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.strategy;

/**
 * 无促销，正常价格
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
public class NormalPromotion implements PromotionStrategy {
    @Override
    public double calcutePrice(double money) {
        return money;
    }
}
