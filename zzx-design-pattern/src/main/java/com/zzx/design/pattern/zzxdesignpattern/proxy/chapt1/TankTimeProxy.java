package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
@Slf4j
public class TankTimeProxy implements Moveable {

    public TankTimeProxy(Tank t) {
        super();
        this.t = t;
    }

    Tank t;

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        t.move();
        long end = System.currentTimeMillis();
        log.info("Tank move time is {}", (end - start));
    }
}
