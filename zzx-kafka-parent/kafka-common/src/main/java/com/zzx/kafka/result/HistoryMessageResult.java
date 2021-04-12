package com.zzx.kafka.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 历史会话记录
 * @author: zhouzhixiang
 * @date: 2019-12-11
 * @company: 叮当快药科技集团有限公司
 **/
@Data
public class HistoryMessageResult implements Serializable {

    private static final long serialVersionUID = 7303646822966919415L;
    /**
     * Message ID 主键, 自增
     */
    private long id;


    /**
     * 消息类型：0-文本，1-图片，2-音频，3-视频，4-文件，5-地理位置，6-自定义，7-提醒，8-AI机器人，9-群通知
     */
    private int msgType;

    /**
     * 提醒类型：0-客服进入，1-客户进入，2-客服进入欢迎提醒，3-敏感词命中提醒消息
     */
    private int remindType;

    /**
     * 内容
     */
    private String content;

    /**
     * 发送人类型：0-用户，1-客服，2-药师，3-医生
     */
    private int sendType;

    /**
     * 来源类型：0-群组，1-点对点，2-聊天室
     */
    private int sourceType;

    /**
     * 来源id
     */
    private long sourceId;

    /**
     * 发送人id
     */
    private long sendId;

    /**
     * 发送人accid
     */
    private String sendAccid;

    /**
     * 发送时间
     */
    private long sendAt;

    /**
     * 是否撤销：0-否，1-是
     */
    private int isRevoke;

    /**
     * 撤销时间
     */
    private long revokeAt;

    /**
     * 状态：有效 0：无效
     */
    private int status;
}
