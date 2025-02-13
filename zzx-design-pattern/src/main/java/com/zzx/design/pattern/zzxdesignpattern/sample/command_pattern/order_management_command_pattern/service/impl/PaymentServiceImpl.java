package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service.impl;

import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean.Order;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean.PaymentFlow;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    
    @Override
    public void createPaymentFlow(Order order) {
        log.info("创建支付流水：orderNo={}", order.getOrderNo());
        // 实际项目中需要调用支付系统
    }

    @Override
    public void cancelPaymentFlow(Long orderId) {
        log.info("取消支付流水：orderId={}", orderId);
    }

    @Override
    public PaymentFlow getPaymentFlow(Long orderId) {
        return new PaymentFlow();
    }
}