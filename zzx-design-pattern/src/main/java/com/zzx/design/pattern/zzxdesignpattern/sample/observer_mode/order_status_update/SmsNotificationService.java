package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.order_status_update;

import org.springframework.stereotype.Component;

/**
 * @Description: 短信通知服务
 * @author: 周志祥
 * @create: 2024-12-03 13:42
 */
@Component
public class SmsNotificationService implements OrderObserver {
    @Override
    public void update(String orderId, String status) {
        System.out.println("【短信通知】订单" + orderId + " 状态更新为：" + status);
        // 调用短信网关API。。。
    }
}
