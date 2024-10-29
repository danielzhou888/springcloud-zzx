package com.zzx.design.pattern.zzxdesignpattern.sample.adaptor_mode.unified_payment_method;

/**
 * 微信支付SDK
 * @author 周志祥
 */
public class WeChatPaySDK {

    public void makePayment(double amount) {
        System.out.println("使用微信支付：" + amount + "元");
    }

    public void processRefund(double amount) {
        System.out.println("使用微信退款：" + amount + "元");
    }
}
