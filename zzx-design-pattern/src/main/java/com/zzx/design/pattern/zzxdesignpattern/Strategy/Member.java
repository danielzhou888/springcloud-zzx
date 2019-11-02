package com.zzx.design.pattern.zzxdesignpattern.Strategy;

/**
 * 会员策略类
 */
public interface Member {

    /**
     * 折扣计算
     * @return
     */
    double bookCalcute(double bookPrice);
}
