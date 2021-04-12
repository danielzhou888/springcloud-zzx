package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.strategy_and_simple_factory;

import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.strategy.DiscountPromotion;
import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.strategy.NormalPromotion;
import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.strategy.PromotionStrategy;
import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.strategy.ReturnPromotion;

/**
 * 促销Context类
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
public class PromotionContext {

    private PromotionStrategy promotionStrategy;

    public PromotionContext(final String type) {
        if ("正常收费".equals(type)) {
            promotionStrategy = new NormalPromotion();
        } else if ("满300返100".equals(type)) {
            promotionStrategy = new ReturnPromotion(300, 100);
        } else if ("打8折".equals(type)) {
            promotionStrategy = new DiscountPromotion(0.8);
        }
    }

    public double calcute(double money) {
        return promotionStrategy.calcutePrice(money);
    }
}
