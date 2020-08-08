package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
@Slf4j
public class Tank implements Moveable {
    public Tank() {
    }

    @Override
    public void move() {
        log.info("Tank moving ...");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
