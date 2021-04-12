package com.zzx.kafka.enums;

/**
 * @description: 消息事件类型
 * @author: zhouzhixiang
 * @date: 2019-12-12
 * @company: 叮当快药科技集团有限公司
 **/
public enum MessageEventTypeEnum {
    P2P(1, "点对点"),
    IMAGE(1, "图片消息"),
    AUDIO(2, "音频消息"),
    VIDEO(3, "视频消息"),
    FILE(4, "文件消息"),
    GEO(5, "地理位置消息"),
    CUSTOM(6, "自定义消息"),
    TIP(7, "提醒消息"),
    ROBOT(8, "AI机器人消息"),
    NOTIFICATION(9, "群通知消息");

    private int code;

    private String name;

    MessageEventTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
