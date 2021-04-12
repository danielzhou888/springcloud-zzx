package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.strategy;

/**
 * 促销Context类
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
public class PromotionContext {

    private PromotionStrategy promotionStrategy;

    public PromotionContext(final PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public double calcute(double money) {
        return promotionStrategy.calcutePrice(money);
    }
}
