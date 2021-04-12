package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt4_cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理工厂
 * @author zhouzhixiang
 * @Date 2020-12-12
 */
public class ProxyFactory implements MethodInterceptor {

    private Object target;

    public ProxyFactory(final Object target) {
        this.target = target;
    }

    public Object getProxuInstance() {
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开启事务");
        Object returnValue = method.invoke(target, objects);
        System.out.println("结束事务");
        return returnValue;
    }
}
