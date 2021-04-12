package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.simple_factory_mode;

/**
 * 促销工厂类
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
public class PromotionFactory {

    public static PromotionSuper createPromotion(String type) {
        PromotionSuper promotionSuper = null;
        if ("正常收费".equals(type)) {
            promotionSuper = new NormalPromotion();
        } else if ("满300返100".equals(type)) {
            promotionSuper = new ReturnPromotion(300, 100);
        } else if ("打8折".equals(type)) {
            promotionSuper = new DiscountPromotion(0.8);
        }
        return promotionSuper;
    }
}
