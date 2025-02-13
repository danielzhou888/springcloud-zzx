package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.order_status_update;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description: 订单状态变更观察者配置
 * @author: 周志祥
 * @create: 2024-12-06 14:37
 */
@Component
public class ObserverConfig {

    private final OrderService orderService;
    private final SmsNotificationService smsNotificationService;
    private final InventoryService inventoryService;
    private final UserMessageCenterService userMessageCenterService;

    public ObserverConfig(OrderService orderService, SmsNotificationService smsNotificationService, InventoryService inventoryService, UserMessageCenterService userMessageCenterService) {
        this.orderService = orderService;
        this.smsNotificationService = smsNotificationService;
        this.inventoryService = inventoryService;
        this.userMessageCenterService = userMessageCenterService;
    }

    @PostConstruct
    public void init() {
        orderService.registerObserver(smsNotificationService);
        orderService.registerObserver(inventoryService);
        orderService.registerObserver(userMessageCenterService);
    }
}
