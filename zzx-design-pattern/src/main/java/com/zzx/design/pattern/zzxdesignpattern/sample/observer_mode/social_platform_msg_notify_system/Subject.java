package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.social_platform_msg_notify_system;

/**
 * 主题接口
 */
public interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(Event event);
}
