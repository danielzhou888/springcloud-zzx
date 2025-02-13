package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.service;

import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean.Order;
import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean.PaymentFlow;

public interface PaymentService {
    void createPaymentFlow(Order order);
    void cancelPaymentFlow(Long orderId);
    PaymentFlow getPaymentFlow(Long orderId);
}