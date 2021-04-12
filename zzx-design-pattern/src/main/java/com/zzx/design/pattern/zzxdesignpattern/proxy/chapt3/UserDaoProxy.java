package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhouzhixiang
 * @Date 2020-12-12
 */
public class UserDaoProxy {

    private IUserDao iUserDao;

    public UserDaoProxy(final IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }

    public IUserDao getInstance() {
        return (IUserDao) Proxy.newProxyInstance(iUserDao.getClass().getClassLoader(), iUserDao.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开启事务");
                Object result = method.invoke(iUserDao, args);
                System.out.println("提交事务");
                return result;
            }
        });
    }
}
