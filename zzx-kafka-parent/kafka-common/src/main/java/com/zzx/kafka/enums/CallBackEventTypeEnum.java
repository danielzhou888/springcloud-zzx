package com.zzx.kafka.enums;

/**
 * 回调事件类型
 */
public enum CallBackEventTypeEnum {

    P2P_MSG(1, "P2P消息回调"),
    TEAM_MSG(2, "群组消息回调"),
    USER_PROFILE_CHANGED(3, "用户资料变更回调"),
    ADD_FRIEND(4, "添加好友回调"),
    DELETE_FRIEND(5, "删除好友回调"),
    CHAT_ROOM_MSG(6, "聊天室消息回调"),
    TEAM_CREATE(7, "创建群回调"),
    TEAM_DISBAND(8, "解散群回调"),
    TEAM_INVITE(9, "群邀请回调"),
    TEAM_QUIT(10, "退群回调"),
    TEAM_ADD_MANAGER(11, "增加群管理员回调"),
    TEAM_CANCEL_MANAGER(12, "取消群管理员回调"),
    TEAM_TRANSFER(13, "转让群回调"),
    TEAM_KICK_OUT(14, "踢人出群回调"),
    TEAM_UPDATE_MSG(15, "更新群信息回调"),
    TEAM_UPDATE_MEMBERS(16, "更新群成员信息回调"),
    TEAM_UPDATE_OTHERS(17, "更新其他人的群成员信息回调"),
    TEAM_BAN_MEMBERS(18, "禁言群成员回调"),
    TEAM_ASK(19, "申请入群回调");

    private int code;

    private String name;

    CallBackEventTypeEnum(int code, String name) {
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
