package com.zzx.design.pattern.zzxdesignpattern.samples.vip;

/**
 * @author zhouzhixiang
 * @Date 2020-09-23
 */
public interface ServiceProvider<T extends User> {

    void service(T user);
}
