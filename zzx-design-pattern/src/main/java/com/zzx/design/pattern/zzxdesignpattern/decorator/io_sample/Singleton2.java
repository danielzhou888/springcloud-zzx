package com.zzx.design.pattern.zzxdesignpattern.decorator.io_sample;

/**
 * @author zhouzhixiang
 * @Date 2020-09-23
 */
public class Singleton2 {

    private static Singleton2 singleton = null;

    enum SINGLETON {
        INSTANCE;
        private Singleton2 singleton;
        SINGLETON() {
            singleton = new Singleton2();
        }
        public Singleton2 getInstance() {
            return singleton;
        }
    }

    public static Singleton2 getInstance() {
        return SINGLETON.INSTANCE.getInstance();
    }

}
