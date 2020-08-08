package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
@Slf4j
public class LogHandler implements InvocationHandler {

    private Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public void invoke(Object o, Method m) {
        log.info("LogHandler log start ... ");
        try {
            m.invoke(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        log.info("LogHandler log end ... ");
    }
}
