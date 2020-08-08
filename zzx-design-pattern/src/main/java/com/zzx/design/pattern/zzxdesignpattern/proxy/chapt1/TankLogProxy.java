package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
@Slf4j
public class TankLogProxy implements Moveable {

    Tank t;

    public TankLogProxy(Tank t) {
        this.t = t;
    }

    @Override
    public void move() {
        log.info("Tank start ...");
        t.move();
        log.info("Tank stop ...");
    }
}
