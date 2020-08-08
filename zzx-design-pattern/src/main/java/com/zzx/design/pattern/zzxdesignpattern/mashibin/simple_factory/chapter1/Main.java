package com.zzx.design.pattern.zzxdesignpattern.mashibin.simple_factory.chapter1;

import org.junit.Test;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-29
 */
public class Main {

    @Test
    public void test() {
        String userName = "zhouzhixiang";
        String password = "123";
        boolean success = LoginManager.init(1).login(userName, password);
        if (success) {
            System.out.println("登陆成功");
        }
    }
}
