package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.simple_factory_mode;

/**
 * 返利促销
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
public class ReturnPromotion implements PromotionSuper {

    private double moneyCondition = 0;
    private double moneyReturn = 0;

    public ReturnPromotion(final double moneyCondition, final double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public double calcutePrice(double money) {
        double result = money;
        if (money > moneyCondition) {
            result = money - money / moneyCondition * moneyReturn;
        }
        return result;
    }
}
