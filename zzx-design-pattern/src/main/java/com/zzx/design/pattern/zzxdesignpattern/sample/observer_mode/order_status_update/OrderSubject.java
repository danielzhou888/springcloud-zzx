package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.order_status_update;

/**
 * @Description: 目标接口
 * @author: 周志祥
 * @create: 2024-12-06 14:30
 */
public interface OrderSubject {

    void registerObserver(OrderObserver observer);

    void removeObserver(OrderObserver observer);

    void notifyObservers(String orderId, String status);
}
