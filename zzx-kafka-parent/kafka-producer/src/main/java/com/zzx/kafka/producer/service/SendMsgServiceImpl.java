package com.zzx.kafka.producer.service;

import com.alibaba.fastjson.JSONObject;
import com.zzx.kafka.enums.SceneEnum;
import com.zzx.kafka.producer.api.SendMsgServiceApi;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @description: 发送消息API
 * @author: zhouzhixiang
 * @date: 2019-12-12
 * @company: 叮当快药科技集团有限公司
 **/
@Service
public class SendMsgServiceImpl implements SendMsgServiceApi {

    protected Logger logger = LoggerFactory.getLogger(SendMsgServiceImpl.class);

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Override
    @Async(value = "asyncTaskExecutor")
    public void sendMsg(String topic, String data) {
        logger.info("SendMsgService sendMsg start");
        ListenableFuture<SendResult<Integer, String>> send = kafkaTemplate.send(topic, data);
        send.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                // TODO  失败重试 处理
                // TODO  告警
                logger.error("SendMsgService sendMsg error, ex = {}, topic = {}, data = {}", new Object[]{throwable, topic, data});
                //redisCacheApi.putMsgToCacheWhenSendKafkaError(topic, data);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> integerStringSendResult) {
                logger.info("SendMsgService sendMsg success, topic = {}, data = {}", topic, data);
            }
        });
        logger.info("SendMsgService sendMsg end");
    }

    @Deprecated
    private void updateTeamUserLastMsgTime(String data) {
        JSONObject map = JSONObject.parseObject(data);
        String convType = String.valueOf(map.get("convType"));
        String to = String.valueOf(map.get("to"));
        try {
            if (StringUtils.isNotBlank(convType) && convType.equals(SceneEnum.TEAM.getName())) {
                //redisCacheApi.updateTeamUserLastMsgTime(to);
            }
        } catch (Exception e) {
            logger.error("processMessage error = {}", e);
        }
    }
}
