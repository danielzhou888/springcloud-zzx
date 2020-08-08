package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt1;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
public class Client {
    public static void main(String[] args) {
        Tank t = new Tank();
        TankTimeProxy ttp = new TankTimeProxy(t);
        TankLogProxy tlp = new TankLogProxy(t);
        Moveable m = tlp;
        m.move();
    }
}
