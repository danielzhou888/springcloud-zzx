package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion1.strategy_and_simple_factory;

/**
 * 促销策略父类
 * @author zhouzhixiang
 * @Date 2020-11-25
 */
public interface PromotionStrategy {

    double calcutePrice(double price);
}
