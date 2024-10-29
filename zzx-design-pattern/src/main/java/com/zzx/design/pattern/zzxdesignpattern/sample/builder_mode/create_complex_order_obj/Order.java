package com.zzx.design.pattern.zzxdesignpattern.sample.builder_mode.create_complex_order_obj;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 订单对象
 * @author: 周志祥
 * @create: 2024-10-28 15:05
 */
public class Order {

    private String orderId;
    private List<String> items = new ArrayList<>();
    private String shippingAddress;
    private String paymentMethod;
    private boolean isGift;
    private String giftMessage;

    public Order(String orderId, List<String> items, String shippingAddress, String paymentMethod, boolean isGift, String giftMessage) {
        this.orderId = orderId;
        this.items = items;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.isGift = isGift;
        this.giftMessage = giftMessage;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", items=" + items +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", isGift=" + isGift +
                ", giftMessage='" + giftMessage + '\'' +
                '}';
    }
}
