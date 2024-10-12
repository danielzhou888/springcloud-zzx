package com.zzx.design.pattern.zzxdesignpattern.sample.factory_mode.online_pay_platform;

/**
 * https://app.yinxiang.com/fx/0cdc7972-e670-48b0-b6a8-31e28ad9187e
 * @Description: 客户端测试
 * @author: 周志祥
 * @create: 2024-10-12 15:58
 */
public class Main {
    public static void main(String[] args) {

        // 用户在美国选择了信用卡支付
        Payment paymentUSA = PaymentFactory.getPayment("USA", "CreditCard");
        paymentUSA.processPayment(100.00);

        // 用户在中国选择了电子钱包支付
        Payment paymentChina = PaymentFactory.getPayment("China", "Ewallet");
        paymentChina.processPayment(200.00);
    }
}
