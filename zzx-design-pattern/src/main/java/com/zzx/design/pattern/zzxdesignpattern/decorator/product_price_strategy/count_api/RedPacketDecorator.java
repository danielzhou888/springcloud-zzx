package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.count_api;

import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.OrderDetail;
import com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po.PromotionType;

import java.math.BigDecimal;

/**
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
public class RedPacketDecorator extends BaseCountDecorator {

    public RedPacketDecorator(IBaseCount baseCount) {
        super(baseCount);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail detail) {
        super.countPayMoney(detail);
        BigDecimal redPacket = detail.getProduct().getSupportPromotions().get(PromotionType.REDPACKED).getUserRedPacket().getRedPacket();
        detail.setPayMoney(detail.getPayMoney().subtract(redPacket));
        return detail.getPayMoney();
    }
}
