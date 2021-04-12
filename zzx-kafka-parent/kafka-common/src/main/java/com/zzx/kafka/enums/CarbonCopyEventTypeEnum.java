package com.zzx.kafka.enums;

/**
 * @description: 抄送消息事件类型
 * @author: zhouzhixiang
 * @date: 2019-12-19
 * @company: 叮当快药科技集团有限公司
 **/
public enum CarbonCopyEventTypeEnum {


    CONVERSATION(1, "会话类型消息"),
    LOGIN(2, "用户登录事件消息"),
    LOGOUT(3, "用户登出/离线事件消息"),
    CHAT_ROOM(4, "聊天室聊天消息"),
    AUDIO_VEDIO_DATA_TUNNEL_TIME(5, "汇报实时音视频通话时长、白板事件时长的消息"),
    AUDIO_VEDIO_DATA_TUNNEL_SIZE(6, "汇报音视频/白板文件的大小、下载地址等消息"),
    SINGLE_WITHDRAW(7, "单聊消息撤回抄送"),
    GROUP_WITHDRAW(8, "群聊消息撤回抄送"),
    CHAT_ROOM_INOUT(9, "汇报主播或管理员进出聊天室事件消息"),
    ECP_CALLBACK(10, "汇报专线电话通话结束回调抄送的消息"),
    SMS_CALLBACK(11, "汇报短信回执抄送的消息"),
    SMS_REPLY(12, "汇报短信上行消息"),
    AV_ROOM_INOUT(13, "汇报用户进出音视频/白板房间的消息"),
    CHAT_ROOM_QUEUE_OPERATE(14, "汇报聊天室队列操作的事件消息"),
    YI_DUN_GARBAGE(20, "易盾异步反垃圾结果信息");

    private int code;

    private String name;

    CarbonCopyEventTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNamebyCode(int code) {
        CarbonCopyEventTypeEnum[] values = CarbonCopyEventTypeEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].code == code) {
                return values[i].name;
            }
        }
        return "";
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
