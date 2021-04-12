package com.zzx.kafka.enums;

/**
 * 场景枚举
 */
public enum SceneEnum {

    P2P(1, "p2p"),
    TEAM(2, "team"),
    SUPER_TEAM(3, "superTeam");


    private int code;

    private String name;

    SceneEnum(int code, String name) {
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
