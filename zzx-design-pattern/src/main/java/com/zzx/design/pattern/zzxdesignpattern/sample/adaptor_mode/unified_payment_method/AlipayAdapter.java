package com.zzx.design.pattern.zzxdesignpattern.sample.adaptor_mode.unified_payment_method;

public class AlipayAdapter implements Payment {

    private AlipaySDK payClient;

    public AlipayAdapter() {
        payClient = new AlipaySDK();
    }

    @Override
    public void pay(double amount) {
        payClient.makePayment(amount);
    }

    @Override
    public void refund(double amount) {
        payClient.processRefund(amount);
    }
}
