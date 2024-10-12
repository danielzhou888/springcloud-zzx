package com.zzx.design.pattern.zzxdesignpattern.sample.factory_mode.online_pay_platform;

/**
 * @Description: 支付基类接口
 * @author: 周志祥
 * @create: 2024-10-12 15:42
 */
public interface Payment {

    /**
     * 支付
     * @param amount
     */
    void processPayment(double amount);
}
