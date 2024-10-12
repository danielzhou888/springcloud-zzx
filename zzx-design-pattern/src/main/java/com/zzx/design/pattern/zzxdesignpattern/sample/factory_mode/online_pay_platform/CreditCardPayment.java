package com.zzx.design.pattern.zzxdesignpattern.sample.factory_mode.online_pay_platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 信用卡支付类
 * @author: 周志祥
 * @create: 2024-10-12 15:47
 */
public class CreditCardPayment implements Payment {

    protected static final Logger logger = LoggerFactory.getLogger(CreditCardPayment.class);

    private String gateway;

    public CreditCardPayment(String gateway) {
        this.gateway = gateway;
    }

    @Override
    public void processPayment(double amount) {
        logger.info("信用卡支付, 网关: {}, 金额: {}", gateway, amount);
    }
}
