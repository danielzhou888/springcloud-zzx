package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
public class Client {

    public static void main(String[] args) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Tank t = new Tank();
        //InvocationHandler handler = new TimeHandler(t);
        InvocationHandler handler = new LogHandler(t);
        Moveable moveable = (Moveable) Proxy.newProxyInstance(Moveable.class, handler);
        moveable.move();
    }
}
