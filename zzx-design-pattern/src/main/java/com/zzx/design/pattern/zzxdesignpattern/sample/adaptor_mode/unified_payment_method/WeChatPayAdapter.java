package com.zzx.design.pattern.zzxdesignpattern.sample.adaptor_mode.unified_payment_method;

public class WeChatPayAdapter implements Payment {

    private WeChatPaySDK payClient;

    public WeChatPayAdapter() {
        this.payClient = new WeChatPaySDK();
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
