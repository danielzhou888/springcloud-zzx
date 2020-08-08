package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt1;

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
        log.info("Task moving ....");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
