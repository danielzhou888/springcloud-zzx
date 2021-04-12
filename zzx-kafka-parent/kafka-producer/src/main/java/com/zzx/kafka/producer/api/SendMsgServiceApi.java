package com.zzx.kafka.producer.api;

/**
 * @description: 发送消息API
 * @author: zhouzhixiang
 * @date: 2019-12-12
 * @company: 叮当快药科技集团有限公司
 **/
public interface SendMsgServiceApi {

    /**
     * 发送消息
     * @param topic
     * @param data
     * @author zhouzhixiang
     */
    void sendMsg(String topic, String data);
}
