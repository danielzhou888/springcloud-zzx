package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service.impl;

import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.OrderCreateDTO;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean.Order;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.enums.OrderStatus;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    
    @Override
    public Order createOrder(OrderCreateDTO orderDTO) {
        Order order = new Order();
        order.setOrderNo(generateOrderNo());
        order.setUserId(orderDTO.getUserId());
        order.setSkuId(orderDTO.getSkuId());
        order.setQuantity(orderDTO.getQuantity());
        order.setStatus(OrderStatus.CREATED);
        order.setCreateTime(new Date());
        // 实际项目中这里需要调用OrderRepository保存订单
        log.info("创建订单成功：{}", order.getOrderNo());
        return order;
    }

    @Override
    public void cancelOrder(Long orderId, String reason) {
        log.info("取消订单：orderId={}, reason={}", orderId, reason);
    }

    @Override
    public Order getOrder(Long orderId) {
        // 实际项目中需要从数据库查询
        return new Order();
    }

    private String generateOrderNo() {
        return "OD" + System.currentTimeMillis();
    }
}