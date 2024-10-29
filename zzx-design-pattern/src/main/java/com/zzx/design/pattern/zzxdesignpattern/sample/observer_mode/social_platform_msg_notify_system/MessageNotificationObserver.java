package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.social_platform_msg_notify_system;

/**
 * 具体观察者：消息通知服务
 */
public class MessageNotificationObserver implements Observer {
    @Override
    public void update(Event event) {
        if (event.getEventType() == EventType.LIKE || event.getEventType() == EventType.COMMENT) {
            System.out.println("消息通知服务：发送消息通知，数据：" + event.getData());
        }
    }
}
