package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.count_api;

import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.OrderDetail;

import java.math.BigDecimal;

/**
 * 计算支付金额的抽象类
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
public abstract class BaseCountDecorator implements IBaseCount {

    private IBaseCount baseCount;

    public BaseCountDecorator(IBaseCount baseCount) {
        this.baseCount = baseCount;
    }


    @Override
    public BigDecimal countPayMoney(OrderDetail detail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        if (baseCount != null) {
            payTotalMoney = baseCount.countPayMoney(detail);
        }
        return payTotalMoney;
    }
}
