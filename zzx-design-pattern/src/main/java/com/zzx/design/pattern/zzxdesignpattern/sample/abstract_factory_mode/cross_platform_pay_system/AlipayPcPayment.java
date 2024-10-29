package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.cross_platform_pay_system;

/**
 * @Description:
 * @author: 周志祥
 * @create: 2024-10-17 16:40
 */
public class AlipayPcPayment implements PcPayment {
    @Override
    public void pCPay() {
        System.out.println("使用支付宝PC支付");
    }
}
