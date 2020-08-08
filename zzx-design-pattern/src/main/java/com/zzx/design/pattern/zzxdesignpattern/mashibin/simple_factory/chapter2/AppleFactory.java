package com.zzx.design.pattern.zzxdesignpattern.mashibin.simple_factory.chapter2;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
public class AppleFactory extends MobileFactory {
    @Override
    public Mobile createMobile() {
        return new AppleMobile();
    }
}
