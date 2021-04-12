package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt4_cglib;

/**
 * @author zhouzhixiang
 * @Date 2020-12-12
 */
public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("存储数据");
    }
}
