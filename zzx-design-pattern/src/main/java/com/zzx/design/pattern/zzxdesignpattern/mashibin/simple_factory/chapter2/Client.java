package com.zzx.design.pattern.zzxdesignpattern.mashibin.simple_factory.chapter2;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
public class Client {

    public static void main(String[] args) {
        MobileFactory factory = new XiaoMiFactory();
        Mobile mobile = factory.createMobile();
        mobile.call();
    }
}
