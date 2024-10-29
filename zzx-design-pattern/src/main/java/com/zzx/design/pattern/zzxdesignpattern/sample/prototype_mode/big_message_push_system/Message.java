package com.zzx.design.pattern.zzxdesignpattern.sample.prototype_mode.big_message_push_system;

/**
 * 可克隆的消息接口
 * @author 周志祥
 */
public interface Message extends Cloneable {

    Message clone();
    void setRecipient(String recipient);
    void setContent(String content);
    void send();
}
