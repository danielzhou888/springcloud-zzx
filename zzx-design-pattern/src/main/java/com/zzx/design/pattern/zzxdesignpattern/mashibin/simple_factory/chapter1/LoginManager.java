package com.zzx.design.pattern.zzxdesignpattern.mashibin.simple_factory.chapter1;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-29
 */
public class LoginManager {

    public static BaseLogin init(Integer type) {
        if (type == 0) {
            return new UserAndPasswordLogin();
        } else if (type == 1){
            return new DomainLogin();
        }
        throw new RuntimeException("非法登陆");
    }
}
