package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
@Data
public class UserCoupon {

    private long id;
    private long userId;
    private String sku;
    // 优惠金额
    private BigDecimal coupon;
}
