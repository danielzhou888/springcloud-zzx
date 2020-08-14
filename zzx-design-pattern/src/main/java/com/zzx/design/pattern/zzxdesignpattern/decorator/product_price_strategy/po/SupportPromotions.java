package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po;

import lombok.Data;

/**
 * 支持的促销
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
@Data
public class SupportPromotions implements Cloneable {

    private long id;
    private PromotionType promotionType;
    private int priority;
    // 用户领取该商品的优惠券
    private UserCoupon userCoupon;
    // 用户领取该商品的红包
    private UserRedPacket userRedPacket;

    @Override
    public SupportPromotions clone() {
        SupportPromotions supportPromotions = null;
        try {
            supportPromotions = (SupportPromotions) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return supportPromotions;
    }
}
