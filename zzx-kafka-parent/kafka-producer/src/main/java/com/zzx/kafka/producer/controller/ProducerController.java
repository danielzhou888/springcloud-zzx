package com.zzx.kafka.producer.controller;

import com.zzx.kafka.enums.CallbackEnum;
import com.zzx.kafka.producer.api.SendMsgServiceApi;
import com.zzx.kafka.producer.config.ImNimConfig;
import com.zzx.kafka.result.CallbackResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 抄送消息
 * @author: zhouzhixiang
 * @date: 2019-12-6
 **/
@RestController
@RequestMapping("produce")
public class ProducerController {

	private Logger logger = LoggerFactory.getLogger(ProducerController.class);

	@Autowired
	private ImNimConfig imNimConfig;

	@Autowired
	private SendMsgServiceApi sendMsgServiceApi;

	@PostMapping("sendMsg")
	public CallbackResult sendMsg(HttpServletRequest request) {
		CallbackResult result = new CallbackResult();
		String uri = "", url = "", ip = "", params = "", ipAddress = "", host = "", curTime = "", checkSum = "", checkSumN = "", requestBody = "", nonce = "";
		int port = 0;
		try {
			for (int i = 0; i < 1000; i++) {
				requestBody = "{\"id\":"+i+", \"name\": \"name-"+i+"\"}";
				sendMsgServiceApi.sendMsg(imNimConfig.getMessageTopic(), requestBody);
			}
			result.setErrCode(CallbackEnum.SUCCESS.getCode());
		} catch (Exception e) {
			result.setErrCode(CallbackEnum.FAIL.getCode());
			logger.error("CarbonCopyController recevMsg error = {}", e);
		} finally {
			logger.info("CallbackResult recevMsg uri = {}, url = {}, ip = {}, params = {}, host = {}, port = {}, ipAddress = {}, requestBody = {}, nonce = {}, checkSumN = {}, errorCode = {}", new Object[]{uri, url, ip, params, host, port, ipAddress, requestBody, nonce, checkSumN, result.getErrCode()});
			return result;
		}
	}

}
