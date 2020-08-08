package com.zzx.design.pattern.zzxdesignpattern.mashibin.simple_factory.chapter1;

/**
 * 用户名密码登陆
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-29
 */
public class UserAndPasswordLogin implements BaseLogin {

    @Override
    public boolean login(String username, String password) {
        return true;
    }
}
