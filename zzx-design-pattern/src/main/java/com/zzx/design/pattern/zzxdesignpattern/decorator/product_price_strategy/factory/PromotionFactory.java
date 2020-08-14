package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.factory;

import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.count_api.BaseCount;
import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.count_api.CouponDecorator;
import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.count_api.IBaseCount;
import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.count_api.RedPacketDecorator;
import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.OrderDetail;
import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.PromotionType;
import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.SupportPromotions;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
public class PromotionFactory {

    public static BigDecimal getPayMoney(OrderDetail orderDetail) {

        Map<PromotionType, SupportPromotions> supportPromotions = orderDetail.getProduct().getSupportPromotions();
        IBaseCount baseCount = new BaseCount();
        if (supportPromotions != null && supportPromotions.size() > 0) {
            for (PromotionType promotionType : supportPromotions.keySet()) {
                if (promotionType == PromotionType.COUPON) {
                    baseCount = new CouponDecorator(baseCount);
                } else if (promotionType == PromotionType.REDPACKED) {
                    baseCount = new RedPacketDecorator(baseCount);
                }
            }
        }
        return baseCount.countPayMoney(orderDetail);
    }
}
