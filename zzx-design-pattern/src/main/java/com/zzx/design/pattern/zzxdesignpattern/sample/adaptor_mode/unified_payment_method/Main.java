package com.zzx.design.pattern.zzxdesignpattern.sample.adaptor_mode.unified_payment_method;

/**
 * https://app.yinxiang.com/fx/64b2c540-1eac-44c9-9188-4bccabc96be4
 * 适配器模式-大型电商平台的支付系统统一支付接口
 * @author 周志祥
 */
public class Main {
    public static void main(String[] args) {
        Payment alipayClient = new AlipayAdapter();
        Payment weChatPayClient = new WeChatPayAdapter();

        alipayClient.pay(100.0);
        alipayClient.refund(90.0);

        weChatPayClient.pay(200.0);
        weChatPayClient.refund(100.0);
    }
}
