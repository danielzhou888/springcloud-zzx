package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.order_status_update;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 订单模块（目标类）
 * @author: 周志祥
 * @create: 2024-12-06 14:35
 */
@Component
public class OrderService implements OrderSubject {

    private final List<OrderObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(OrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String orderId, String status) {
        for (OrderObserver observer : observers) {
            observer.update(orderId, status);
        }
    }

    /**
     * 订单状态更新
     * @param orderId
     * @param status
     */
    public void updateOrderStatus(String orderId, String status) {
        System.out.println("订单" + orderId + "状态更新为：" + status);
        notifyObservers(orderId, status);
    }
}
