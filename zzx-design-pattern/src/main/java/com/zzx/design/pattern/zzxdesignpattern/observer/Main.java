package com.zzx.design.pattern.zzxdesignpattern.observer;

import org.junit.Test;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-28
 */
public class Main {

    @Test
    public void test() {
        WeChatUser u1 = new WeChatUser("u1");
        WeChatUser u2 = new WeChatUser("u2");
        WeChatUser u3 = new WeChatUser("u3");
        WeChatUser u4 = new WeChatUser("u4");

        BaseSubject subject = new WechatPublic();

        subject.add(u1);
        subject.add(u2);
        subject.add(u3);
        subject.add(u4);

        subject.notify("公众号首推");

    }
}
