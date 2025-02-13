package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service;

import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.OrderCreateDTO;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean.Order;

public interface OrderService {
    Order createOrder(OrderCreateDTO orderDTO);
    void cancelOrder(Long orderId, String reason);
    Order getOrder(Long orderId);
}