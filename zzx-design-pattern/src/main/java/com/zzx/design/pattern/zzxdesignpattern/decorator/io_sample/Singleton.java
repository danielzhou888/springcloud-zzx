package com.zzx.design.pattern.zzxdesignpattern.decorator.io_sample;

/**
 * @author zhouzhixiang
 * @Date 2020-09-23
 */
public class Singleton {

    private static volatile Singleton singleton = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
