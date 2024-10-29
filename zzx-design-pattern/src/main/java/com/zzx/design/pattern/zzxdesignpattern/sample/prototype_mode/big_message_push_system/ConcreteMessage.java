package com.zzx.design.pattern.zzxdesignpattern.sample.prototype_mode.big_message_push_system;

import java.util.Date;

public class ConcreteMessage implements Message {

    private String sender;
    private String recipient;
    private String content;
    private Date timestamp;

    public ConcreteMessage(String sender) {
        this.sender = sender;
        this.timestamp = new Date();
    }

    @Override
    public Message clone() {
        try {
            ConcreteMessage cloned = (ConcreteMessage) super.clone();
            cloned.timestamp = (Date) this.timestamp.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send() {
        System.out.println("发送消息：");
        System.out.println("发件人：" + sender);
        System.out.println("收件人：" + recipient);
        System.out.println("内容：" + content);
        System.out.println("时间：" + timestamp);
        System.out.println("----------------------");
    }
}
