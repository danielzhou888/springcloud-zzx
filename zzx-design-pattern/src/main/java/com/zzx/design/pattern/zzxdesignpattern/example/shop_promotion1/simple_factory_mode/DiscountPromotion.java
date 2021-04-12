package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.simple_factory_mode;

/**
 * 打折促销
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
public class DiscountPromotion implements PromotionSuper {

    private double discount = 1;

    public DiscountPromotion(final double discount) {
        this.discount = discount;
    }

    @Override
    public double calcutePrice(double money) {
        return money * discount;
    }
}
