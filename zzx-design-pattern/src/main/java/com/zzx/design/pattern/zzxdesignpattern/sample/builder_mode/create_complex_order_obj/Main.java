package com.zzx.design.pattern.zzxdesignpattern.sample.builder_mode.create_complex_order_obj;

/**
 * 代码说明：
 * 1. 订单对象（Order）：包含了所有订单相关的信息。
 * 2. 订单建造者（OrderBuilder）：提供了链式方法来设置订单的各个部分。使用链式调用的方式可以方便地动态构造订单对象。
 * 3. 订单服务（EcommerceOrderService）：是客户端代码，用来演示如何通过建造者模式来构建一个复杂订单对象。
 *
 *
 * 建造者模式的优点：
 * - 分离了对象的构建与表示，使代码更具可读性和可维护性。
 * - 通过链式方法调用，使订单对象的构建更加灵活和清晰。
 *
 * https://app.yinxiang.com/fx/054a9fef-05c2-4c4a-b5a1-32f1b0af9e47
 * @Description: 建造者模式-互联网商城中用于创建复杂订单对象的场景
 * @author: 周志祥
 * @create: 2024-10-28 14:58
 */
public class Main {

    public static void main(String[] args) {
        // 客户端代码，使用建造者模式创建一个复杂订单
        OrderBuilder builder = new OrderBuilder();
        Order order = builder.setOrderId("ORD123456")
                .addItem("Laptop")
                .addItem("Mouse")
                .setShippingAddress("123 Main Street, City, Country")
                .setPaymentMethod("Credit Card")
                .setGift(true)
                .setGiftMessage("Happy Birthday!")
                .build();
        System.out.println(order);
    }
}
