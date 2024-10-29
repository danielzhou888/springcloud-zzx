package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.cross_platform_pay_system;

/**
 * @Description:
 * @author: 周志祥
 * @create: 2024-10-17 16:42
 */
public class PaypalPcPayment implements PcPayment {

    @Override
    public void pCPay() {
        System.out.println("使用PaypalPC支付");
    }
}
