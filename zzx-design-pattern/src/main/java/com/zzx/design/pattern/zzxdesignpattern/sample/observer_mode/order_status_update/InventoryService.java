package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.order_status_update;

import org.springframework.stereotype.Component;

/**
 * @Description: 库存管理服务
 * @author: 周志祥
 * @create: 2024-12-06 14:22
 */
@Component
public class InventoryService implements OrderObserver {
    @Override
    public void update(String orderId, String status) {
        System.out.println("【库存管理】订单" + orderId + " 支付完成，准备扣减库存");
        // 扣减库存逻辑。。。
    }
}
