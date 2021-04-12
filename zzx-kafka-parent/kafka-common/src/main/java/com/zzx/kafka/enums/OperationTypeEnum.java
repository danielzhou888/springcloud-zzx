package com.zzx.kafka.enums;

/**
 * @description: 操作类型——用于日志记录
 * @author: zhouzhixiang
 * @date: 2019-12-10
 * @company: 叮当快药科技集团有限公司
 */
public enum OperationTypeEnum {

    //用户相关
    USER_REGISTER(0, "用户注册"),
    USER_ONLINE(1, "用户上线"),
    USER_OFFLINE(2, "用户下线"),

    //客服相关
    WAITER_REGISTER(100, "客服注册"),
    WAITER_ONLINE(101, "客服上线"),
    WAITER_OFFLINE(102, "客服下线"),

    //会话相关
    SESSION_CREATE(200, "创建会话"),
    SESSION_LINE_UP(201, "进入等待队列"),
    SESSION_LINE_UP_TAKE(202, "从等待队列接待会话"),
    SESSION_ROBOT_TAKE(203, "机器人接待会话"),
    SESSION_TAKE(204,"人工接待会话"),
    SESSION_AUTO_TAKE(205, "自动接入会话"),
    SESSION_TRANSFER(206, "转接客服"),
    SESSION_END(207, "会话结束");

    private int code;

    private String name;

    OperationTypeEnum(int code, String name) {
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
