package com.zzx.design.pattern.zzxdesignpattern.samples.vip;

import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-09-23
 */
public class VipService {

    Map<User.TYPE, ServiceProvider<User>> providers;

    <T extends User> void serviceVip(T user) {
        providers.get(user.getType()).service(user);
    }
}
