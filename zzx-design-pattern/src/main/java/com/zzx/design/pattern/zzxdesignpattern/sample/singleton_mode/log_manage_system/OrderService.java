package com.zzx.design.pattern.zzxdesignpattern.sample.singleton_mode.log_manage_system;

/**
 * @Description: 订单模块Service
 * @author: 周志祥
 * @create: 2024-10-12 15:30
 */
public class OrderService {

    public void createOrder(String orderId) {
        LogManager logManager = LogManager.getInstance();
        logManager.log("创建订单: " + orderId);
    }
}
