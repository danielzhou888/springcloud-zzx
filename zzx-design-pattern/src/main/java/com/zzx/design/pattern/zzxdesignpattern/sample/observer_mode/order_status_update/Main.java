package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.order_status_update;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * https://app.yinxiang.com/fx/de6216c6-6bf9-4833-88d9-04251c6c99fc
 * @Description: 观察者模式-订单状态发生变化时，需要通知多个模块（如短信通知、库存管理、物流系统、用户消息中心等）
 * @author: 周志祥
 * @create: 2024-12-03 13:40
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        OrderService orderService = context.getBean(OrderService.class);

        orderService.updateOrderStatus("111", "PAID"); // 订单已支付
        orderService.updateOrderStatus("222", "SHIPPED"); // 订单已发货
    }
}
