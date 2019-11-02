package com.zzx.design.pattern.zzxdesignpattern.Strategy;

/**
 * 高级会员
 */
public class AdvancedMember implements Member {

    private static final Double discont = 0.8;

    @Override
    public double bookCalcute(double bookPrice) {
        return bookPrice * discont;
    }
}
