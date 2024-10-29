package com.zzx.design.pattern.zzxdesignpattern.sample.observer_mode.social_platform_msg_notify_system;

/**
 * 事件类
 */
public class Event {
    private EventType eventType;
    private String data;

    public Event(EventType eventType, String data) {
        this.eventType = eventType;
        this.data = data;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getData() {
        return data;
    }
}
