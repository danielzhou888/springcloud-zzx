package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.order_status_update;

import org.springframework.stereotype.Component;

/**
 * @Description: 用户消息中心服务
 * @author: 周志祥
 * @create: 2024-12-06 14:28
 */
@Component
public class UserMessageCenterService implements OrderObserver {
    @Override
    public void update(String orderId, String status) {
        System.out.println("【用户消息中心】订单" + orderId + " 状态更新为：" + status + "，推送到用户消息中心");
        // 推送用户通知的逻辑
    }
}
