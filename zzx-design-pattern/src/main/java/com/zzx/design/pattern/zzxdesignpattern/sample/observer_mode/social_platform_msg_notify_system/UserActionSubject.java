package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.social_platform_msg_notify_system;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题
 */
public class UserActionSubject implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(Event event) {
        for (Observer observer :observerList) {
            observer.update(event);
        }
    }

    public void userAction(EventType eventType, String data) {
        System.out.println("用户发生动作：" + eventType.name() + ", 数据: " + data);
        Event event = new Event(eventType, data);
        notifyObservers(event);
    }
}
