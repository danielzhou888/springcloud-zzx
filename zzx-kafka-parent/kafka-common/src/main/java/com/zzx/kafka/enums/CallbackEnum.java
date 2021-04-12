package com.zzx.kafka.enums;

/**
 * @program: 20160219
 * @description: 回调返回枚举
 * @author: zhouzhixiang
 * @date: 2019-12-06
 * @company: 叮当快药科技集团有限公司
 **/
public enum  CallbackEnum {

    SUCCESS(0, "回调通过"),
    FAIL(1, "回调不通过");

    private int code;

    private String name;

    CallbackEnum(int code, String name) {
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
