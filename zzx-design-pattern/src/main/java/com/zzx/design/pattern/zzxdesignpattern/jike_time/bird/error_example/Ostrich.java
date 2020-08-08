package com.zzx.design.pattern.zzxdesignpattern.jike_time.bird.error_example;

/**
 * @author zhouzhixiang
 * @Date 2020-05-12
 */
public class Ostrich extends AbstractBird {

    @Override
    public void fly() {
        throw new UnsupportedOperationException("I can't fly");
    }
}
