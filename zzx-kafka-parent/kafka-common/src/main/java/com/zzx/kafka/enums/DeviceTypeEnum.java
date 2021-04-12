package com.zzx.kafka.enums;

/**
 * @description: 设备类型
 * @author: zhouzhixiang
 * @date: 2019-12-12
 * @company: 叮当快药科技集团有限公司
 **/
public enum DeviceTypeEnum {
    ANDROID(0, "安卓"),
    IOS(1, "苹果"),
    PC(2, "Windows桌面"),
    WEB(3, "浏览器"),
    MAC(4, "Mac桌面");

    private int code;

    private String name;

    DeviceTypeEnum(int code, String name) {
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
