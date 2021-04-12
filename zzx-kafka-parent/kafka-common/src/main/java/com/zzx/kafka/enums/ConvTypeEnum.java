package com.zzx.kafka.enums;

/**
 * ConvType枚举
 */
public enum ConvTypeEnum {

    PERSON(1, "PERSON"),
    TEAM(2, "TEAM"),                      // 群聊会话内消息
    CUSTOM_PERSON(3, "CUSTOM_PERSON"),
    CUSTOM_TEAM(3, "CUSTOM_TEAM");        // 群聊自定义系统通知及内置群聊系统通知


    private int code;

    private String name;

    ConvTypeEnum(int code, String name) {
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
