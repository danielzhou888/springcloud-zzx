//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zzx.sentinel.distribute.enums;

public enum SentinelEnum {
    FLOW_EXCEPTION(-10, "限流异常"),
    DEGRADE_EXCEPTION(-11, "降级异常"),
    PARAM_FLOW_EXCEPTION(-12, "热点参数限流异常"),
    SYSTEM_BLOCK_EXCEPTION(-13, "系统规则异常"),
    AUTHORITY_EXCEPTION(-14, "授权规则异常");

    private int code;
    private String name;

    private SentinelEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static SentinelEnum getEnum(int code) {
        SentinelEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            SentinelEnum e = var1[var3];
            if (code == e.getCode()) {
                return e;
            }
        }

        return null;
    }
}
