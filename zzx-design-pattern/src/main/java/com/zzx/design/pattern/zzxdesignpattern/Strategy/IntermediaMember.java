package com.zzx.design.pattern.zzxdesignpattern.Strategy;

/**
 * 中级会员
 */
public class IntermediaMember implements Member {

    private static final Double discount = 0.8;

    @Override
    public double bookCalcute(double bookPrice) {
        return bookPrice * discount;
    }
}
