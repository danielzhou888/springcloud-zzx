package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
@Slf4j
public class TimeHandler implements InvocationHandler {

    private Object target;

    public TimeHandler(Object target) {
        this.target = target;
    }

    @Override
    public void invoke(Object o, Method m) {
        long start = System.currentTimeMillis();
        log.info("o : " + o.getClass().getName() + " start time : " + start);
        log.info("target : " + target.getClass().getName() + " start time : " + start);
        try {
            m.invoke(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info(target.getClass().getName() + " end time : " + end);
    }
}
