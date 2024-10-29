package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.cross_platform_pay_system;

/**
 * @Description:
 * @author: 周志祥
 * @create: 2024-10-17 16:43
 */
public class WechatFactory implements PaymentFactory {
    @Override
    public MobilePayment createMobilePayment() {
        return new WechatMobilePayment();
    }

    @Override
    public PcPayment createPcPayment() {
        return new WechatPcPayment();
    }
}
