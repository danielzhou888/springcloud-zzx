package com.zzx.kafka.enums;

/**
 * @description: 客户端类型
 * @author: zhouzhixiang
 * @date: 2019-12-12
 * @company: 叮当快药科技集团有限公司
 **/
public enum ClientTypeEnum {


    AOS(0, "AOS"),
    IOS(1, "IOS"),
    PC(2, "PC"),
    WINPHONE(3, "WINPHONE"),
    WEB(4, "WEB"),
    REST(5, "REST");

    private int code;

    private String name;

    ClientTypeEnum(int code, String name) {
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
