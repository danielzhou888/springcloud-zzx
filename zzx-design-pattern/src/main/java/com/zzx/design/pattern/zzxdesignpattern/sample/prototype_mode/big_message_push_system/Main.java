package com.zzx.design.pattern.zzxdesignpattern.sample.prototype_mode.big_message_push_system;

import java.util.Arrays;
import java.util.List;

/**
 * https://app.yinxiang.com/fx/f9d1a904-aeb4-4943-9392-55008bb85903
 * 原型模式-大型即时通讯应用的消息推送系统
 * @author 周志祥
 */
public class Main {
    public static void main(String[] args) {
        // 原型对象
        ConcreteMessage message = new ConcreteMessage("系统");
        message.setContent("欢迎使用XX系统，祝您使用愉快！");

        List<String> recipientorList = Arrays.asList("张三", "李四", "王五");
        for (String recipient :recipientorList) {
            Message clonedMessage = message.clone();
            clonedMessage.setRecipient(recipient);
            clonedMessage.send();
        }
    }
}
