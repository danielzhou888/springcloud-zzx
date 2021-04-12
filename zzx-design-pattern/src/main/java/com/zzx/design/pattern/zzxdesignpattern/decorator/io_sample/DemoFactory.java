package com.zzx.design.pattern.zzxdesignpattern.decorator.io_sample;

/**
 * @author zhouzhixiang
 * @Date 2020-09-23
 */
public enum DemoFactory {

    DEMO_FACTORY;

    private class BeanInstance {

    }

    private BeanInstance getInstance() {
        return new BeanInstance();
    }


    public static void main(String[] args) {
        DemoFactory.DEMO_FACTORY.getInstance();
    }
}
