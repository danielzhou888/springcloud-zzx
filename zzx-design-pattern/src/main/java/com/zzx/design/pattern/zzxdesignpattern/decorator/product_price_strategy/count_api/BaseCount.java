package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.count_api;

import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
@Slf4j
public class BaseCount implements IBaseCount {

    @Override
    public BigDecimal countPayMoney(OrderDetail detail) {
        detail.setPayMoney(detail.getProduct().getPrice());
        log.info("商品原单价金额为：" + detail.getPayMoney());
        return detail.getPayMoney();
    }
}
