package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2;

import java.lang.reflect.Method;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
public interface InvocationHandler {

    void invoke(Object o, Method m);
}
