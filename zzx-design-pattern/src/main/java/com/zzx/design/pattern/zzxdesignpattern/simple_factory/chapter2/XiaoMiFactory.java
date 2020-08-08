package com.zzx.design.pattern.zzxdesignpattern.simple_factory.chapter2;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
public class XiaoMiFactory extends MobileFactory {

    @Override
    public Mobile createMobile() {
        return new XiaoMiMobile();
    }
}
