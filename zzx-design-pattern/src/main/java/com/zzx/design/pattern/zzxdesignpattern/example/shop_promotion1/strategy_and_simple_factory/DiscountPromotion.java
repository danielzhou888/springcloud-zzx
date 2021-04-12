package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.strategy_and_simple_factory;

/**
 * 打折促销
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
public class DiscountPromotion implements PromotionStrategy {

    private double discount = 1;

    public DiscountPromotion(final double discount) {
        this.discount = discount;
    }

    @Override
    public double calcutePrice(double money) {
        return money * discount;
    }
}
