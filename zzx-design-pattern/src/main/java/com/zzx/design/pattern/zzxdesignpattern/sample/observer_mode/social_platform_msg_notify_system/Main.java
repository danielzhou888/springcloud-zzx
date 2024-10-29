package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.social_platform_msg_notify_system;

/**
 * https://app.yinxiang.com/fx/45a83c27-77d3-4b57-8a5a-d2105b3a3068
 * 观察者模式-大型社交媒体平台的消息通知系统
 * @author 周志祥
 */
public class Main {

    public static void main(String[] args) {
        UserActionSubject subject = new UserActionSubject();

        MessageNotificationObserver messageNotificationObserver = new MessageNotificationObserver();
        FeedUpdateObserver feedUpdateObserver = new FeedUpdateObserver();
        AnalyticsObserver analyticsObserver = new AnalyticsObserver();

        subject.attach(messageNotificationObserver);
        subject.attach(feedUpdateObserver);
        subject.attach(analyticsObserver);

        subject.userAction(EventType.POST, "用户发布了一条新动态");
        subject.userAction(EventType.LIKE, "用户点赞了你的动态");
        subject.userAction(EventType.COMMENT, "用户评论了你的动态");
    }
}
