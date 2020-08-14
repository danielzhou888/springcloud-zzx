package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
@Data
public class Order {

    private long id;
    private String orderNo;
    private BigDecimal totalPay;
    // 详细订单列表
    private List<OrderDetail> list;
}
