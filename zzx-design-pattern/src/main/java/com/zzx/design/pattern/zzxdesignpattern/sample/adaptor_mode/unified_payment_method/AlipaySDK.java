package com.zzx.design.pattern.zzxdesignpattern.sample.adaptor_mode.unified_payment_method;

/**
 * 支付宝支付SDK
 * @author 周志祥
 */
public class AlipaySDK {

    public void makePayment(double amount) {
        System.out.println("使用支付宝支付：" + amount + "元");
    }

    public void processRefund(double amount) {
        System.out.println("使用支付宝退款：" + amount + "元");
    }
}
