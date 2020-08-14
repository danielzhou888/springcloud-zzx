package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.count_api;

import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.OrderDetail;
import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.PromotionType;

import java.math.BigDecimal;

/**
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
public class CouponDecorator extends BaseCountDecorator {

    public CouponDecorator(IBaseCount baseCount) {
        super(baseCount);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail detail) {
        super.countPayMoney(detail);
        BigDecimal coupon = detail.getProduct().getSupportPromotions().get(PromotionType.COUPON).getUserCoupon().getCoupon();
        detail.setPayMoney(detail.getPayMoney().subtract(coupon));
        BigDecimal totalPay = detail.getPayMoney();
        return totalPay;
    }

}
