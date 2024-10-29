package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.cross_platform_pay_system;

/**
 * @Description:
 * @author: 周志祥
 * @create: 2024-10-17 16:44
 */
public class PayClient {

    private MobilePayment mobilePayment;
    private PcPayment pcPayment;

    public PayClient(PaymentFactory paymentFactory) {
        this.mobilePayment = paymentFactory.createMobilePayment();
        this.pcPayment = paymentFactory.createPcPayment();
    }

    public void mobilePay() {
        this.mobilePayment.mobilePay();
    }

    public void pcPay() {
        this.pcPayment.pCPay();
    }
}
