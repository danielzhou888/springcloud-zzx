package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.social_platform_msg_notify_system;

/**
 * 具体观察者：统计分析服务
 */
public class AnalyticsObserver implements Observer {
    @Override
    public void update(Event event) {
        System.out.println("统计分析服务：记录用户行为，类型：" + event.getEventType().name());
    }
}
