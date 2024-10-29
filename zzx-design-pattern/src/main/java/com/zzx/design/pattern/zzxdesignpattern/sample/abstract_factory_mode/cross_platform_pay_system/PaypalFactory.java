package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.cross_platform_pay_system;

/**
 * @Description:
 * @author: 周志祥
 * @create: 2024-10-17 16:44
 */
public class PaypalFactory implements PaymentFactory {
    @Override
    public MobilePayment createMobilePayment() {
        return new PaypalMobilePayment();
    }

    @Override
    public PcPayment createPcPayment() {
        return new PaypalPcPayment();
    }
}
