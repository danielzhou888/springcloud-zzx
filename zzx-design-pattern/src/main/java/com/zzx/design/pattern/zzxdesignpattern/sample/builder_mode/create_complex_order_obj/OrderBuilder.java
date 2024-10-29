package com.zzx.design.pattern.zzxdesignpattern.sample.builder_mode.create_complex_order_obj;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 订单构造器
 * @author: 周志祥
 * @create: 2024-10-28 15:07
 */
public class OrderBuilder {

    private String orderId;
    private List<String> items = new ArrayList<>();
    private String shippingAddress;
    private String paymentMethod;
    private boolean isGift;
    private String giftMessage;

    public OrderBuilder setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderBuilder addItem(String item) {
        items.add(item);
        return this;
    }

    public OrderBuilder setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public OrderBuilder setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public OrderBuilder setGift(boolean gift) {
        isGift = gift;
        return this;
    }

    public OrderBuilder setGiftMessage(String giftMessage) {
        this.giftMessage = giftMessage;
        return this;
    }

    public Order build() {
        return new Order(orderId, items, shippingAddress, paymentMethod, isGift, giftMessage);
    }
}
