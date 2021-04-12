package com.zzx.kafka.result;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 用于web调用展示历史会话列表
 * @author: zhouzhixiang
 * @date: 2019-12-11
 * @company: 叮当快药科技集团有限公司
 **/
@Data
@Builder
@NoArgsConstructor
public class HistorySessionResult implements Serializable {

    private static final long serialVersionUID = -3749814144623730797L;
    /** 群id */
    private long teamId;
    /** 未读数 */
    private int unread;
    /** 最后发送人accid */
    private String lastSendAccid;
    /** 最后发送人昵称 */
    private String lastSendNick;
    /** 最后发送消息时间 */
    private Long lastSendTime;
    /** 最后发送消息内容 */
    private String lastText;

    public HistorySessionResult(long teamId, int unread, String lastSendAccid, String lastSendNick, Long lastSendTime, String lastText) {
        this.teamId = teamId;
        this.unread = unread;
        this.lastSendAccid = lastSendAccid;
        this.lastSendNick = lastSendNick;
        this.lastSendTime = lastSendTime;
        this.lastText = lastText;
        this.unread = 0;
    }

}
