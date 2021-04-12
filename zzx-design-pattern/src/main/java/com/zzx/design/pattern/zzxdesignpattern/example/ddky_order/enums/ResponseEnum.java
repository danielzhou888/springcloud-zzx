package com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.enums;

/**
 * <p>响应状态码</p>
 *
 * @author: zhouzhixiang
 * @date: 2020-01-20
 **/
public enum ResponseEnum {

    SUCCESS(0, "success"),
    FAIL(1, "fail")


    ;

    private int code;
    private String name;

    ResponseEnum(int code, String name) {
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
