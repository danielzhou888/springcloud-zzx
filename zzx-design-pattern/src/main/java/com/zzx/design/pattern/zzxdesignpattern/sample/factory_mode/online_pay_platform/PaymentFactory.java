package com.zzx.design.pattern.zzxdesignpattern.sample.factory_mode.online_pay_platform;

import org.apache.commons.lang.StringUtils;

/**
 * @Description: 支付工厂类: 用于生成不同国家和支付方式的支付对象
 * @author: 周志祥
 * @create: 2024-10-12 15:50
 */
public class PaymentFactory {

    public static Payment getPayment(String country, String paymentType) {
        if (StringUtils.equalsIgnoreCase(country, "USA")) {
            if (StringUtils.equalsIgnoreCase(paymentType, "CreditCard")) {
                // 信用卡支付
                return new CreditCardPayment("payPal");
            } else if (StringUtils.equalsIgnoreCase(paymentType, "EWallet")) {
                return new EWalletPayment("Apple Pay");
            }
        } else if (StringUtils.equalsIgnoreCase(country, "China")) {
            if (StringUtils.equalsIgnoreCase(paymentType, "CreditCard")) {
                return new CreditCardPayment("Alipay");
            } else if (StringUtils.equalsIgnoreCase(paymentType, "EWallet")) {
                return new EWalletPayment("WeChat Pay");
            }
        }
        throw new IllegalArgumentException("当前国家不支持支付或当前支付类型不支持");
    }
}
