package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.count_api;

import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.OrderDetail;

import java.math.BigDecimal;

/**
 * 计算支付金额接口类
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
public interface IBaseCount {

    BigDecimal countPayMoney(OrderDetail detail);
}
