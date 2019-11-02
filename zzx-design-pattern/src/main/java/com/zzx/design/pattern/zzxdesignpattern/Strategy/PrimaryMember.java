package com.zzx.design.pattern.zzxdesignpattern.Strategy;

/**
 * 初级会员
 */
public class PrimaryMember implements Member {

    private static final Double discont = 0.9;

    @Override
    public double bookCalcute(double bookPrice) {
        return bookPrice * discont;
    }
}
