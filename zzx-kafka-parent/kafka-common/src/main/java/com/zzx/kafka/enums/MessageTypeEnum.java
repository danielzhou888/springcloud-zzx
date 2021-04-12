package com.zzx.kafka.enums;

/**
 * @description: 消息类型
 * @author: zhouzhixiang
 * @date: 2019-12-12
 * @company: 叮当快药科技集团有限公司
 **/
public enum MessageTypeEnum {

    /** convType为PERSON、TEAM时对应的消息类型： */
    TEXT(0, "TEXT"),
    IMAGE(1, "PICTURE"),
    AUDIO(2, "AUDIO"),
    VIDEO(3, "VIDEO"),
    FILE(4, "FILE"),
    GEO(5, "LOCATION"),
    CUSTOM(6, "CUSTOM"),
    TIP(7, "TIPS"),
    ROBOT(8, "AI"),
    NOTIFICATION(9, "NOTIFICATION"),

    /** convType为CUSTOM_TEAM对应的通知消息类型（请注意与NOTIFICATION区分）： */
    TEAM_APPLY(10, "TEAM_APPLY"),
    TEAM_APPLY_REJECT(11, "TEAM_APPLY_REJECT"),
    TEAM_INVITE(12, "TEAM_INVITE"),
    TEAM_INVITE_REJECT(13, "TEAM_INVITE_REJECT"),
    CUSTOM_TEAM_MSG(14, "CUSTOM_TEAM_MSG");

    private int code;

    private String name;

    MessageTypeEnum(int code, String name) {
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
