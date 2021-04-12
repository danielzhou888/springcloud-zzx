package com.zzx.kafka.api;

/**
 * @description: 接收消息API
 * @author: zhouzhixiang
 * @date: 2019-12-12
 * @company: 叮当快药科技集团有限公司
 **/
public interface RecevMsgServiceApi {

    /**
     * 处理从kafka拉取的消息
     * @param message
     * @author zhouzhixiang
     * @return
     */
    boolean processMessage(String message);
}
