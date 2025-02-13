package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.order_status_update;

/**
 * @Description: 订单观察者
 * @author: 周志祥
 * @create: 2024-12-03 13:41
 */
public interface OrderObserver {

    void update(String orderId, String status);
}
