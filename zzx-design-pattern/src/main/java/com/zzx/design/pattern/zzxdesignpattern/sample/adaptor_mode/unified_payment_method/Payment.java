package com.zzx.design.pattern.zzxdesignpattern.sample.adaptor_mode.unified_payment_method;

/**
 * 支付接口
 */
public interface Payment {
    void pay(double amount);
    void refund(double amount);
}
