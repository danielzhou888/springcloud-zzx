package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.social_platform_msg_notify_system;

/**
 * 具体观察者：动态更新服务
 */
public class FeedUpdateObserver implements Observer {
    @Override
    public void update(Event event) {
        System.out.println("动态更新服务：更新用户动态，数据" + event.getData());
    }
}
