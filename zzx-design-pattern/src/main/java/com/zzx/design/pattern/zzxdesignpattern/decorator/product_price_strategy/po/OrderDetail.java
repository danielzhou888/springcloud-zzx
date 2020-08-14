package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
@Data
public class OrderDetail {

    private long id;
    private long orderId;
    // 商品详情
    private Product product;
    // 支付单价
    private BigDecimal payMoney;
}
