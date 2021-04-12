package com.zzx.kafka.service;

import com.alibaba.fastjson.JSONObject;
import com.zzx.kafka.api.RecevMsgServiceApi;
import com.zzx.kafka.util.JsonUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @description: 接收消息服务
 * @author: zhouzhixiang
 * @date: 2019-12-12
 * @company: 叮当快药科技集团有限公司
 **/
@Service
public class RecevMsgServiceImpl implements RecevMsgServiceApi {

    protected Logger logger = LoggerFactory.getLogger(RecevMsgServiceImpl.class);

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

//    @Reference
//    private WaiterService waiterService;

//    @Reference
//    private WaiterCacheApi waiterCacheApi;

//    @KafkaListener(id = "consumerId1", topics = "#{topicName}", groupId = "#{topicGroupId}")
//    public void recevMsg1(String value) {
//        logger.info("RecevMsgServiceImpl recevMsg message = {}", value);
//        boolean result = false;
//        try {
//             result = this.processMessage(value);
//        } catch (Exception e) {
//            logger.error("RecevMsgServiceImpl recevMsg error: ", e);
//        } finally {
//            logger.info("RecevMsgServiceImpl recevMsg result = {}", result);
//        }
//    }
//
//    @KafkaListener(id = "consumerId2", topics = "#{topicName}", groupId = "#{topicGroupId}")
//    public void recevMsg2(String value) {
//        logger.info("RecevMsgServiceImpl recevMsg message = {}", value);
//        boolean result = false;
//        try {
//            result = this.processMessage(value);
//        } catch (Exception e) {
//            logger.error("RecevMsgServiceImpl recevMsg error: ", e);
//        } finally {
//            logger.info("RecevMsgServiceImpl recevMsg result = {}", result);
//        }
//    }
//
//    @KafkaListener(id = "consumerId3", topics = "#{topicName}", groupId = "#{topicGroupId}")
//    public void recevMsg3(String value) {
//        logger.info("RecevMsgServiceImpl recevMsg message = {}", value);
//        boolean result = false;
//        try {
//            result = this.processMessage(value);
//        } catch (Exception e) {
//            logger.error("RecevMsgServiceImpl recevMsg error: ", e);
//        } finally {
//            logger.info("RecevMsgServiceImpl recevMsg result = {}", result);
//        }
//    }
//
//    @KafkaListener(id = "consumerId4", topics = "#{topicName}", groupId = "#{topicGroupId}")
//    public void recevMsg4(String value) {
//        logger.info("RecevMsgServiceImpl recevMsg message = {}", value);
//        boolean result = false;
//        try {
//            result = this.processMessage(value);
//        } catch (Exception e) {
//            logger.error("RecevMsgServiceImpl recevMsg error: ", e);
//        } finally {
//            logger.info("RecevMsgServiceImpl recevMsg result = {}", result);
//        }
//    }
//
//    @KafkaListener(id = "consumerId5", topics = "#{topicName}", groupId = "#{topicGroupId}")
//    public void recevMsg5(String value) {
//        logger.info("RecevMsgServiceImpl recevMsg message = {}", value);
//        boolean result = false;
//        try {
//            result = this.processMessage(value);
//        } catch (Exception e) {
//            logger.error("RecevMsgServiceImpl recevMsg error: ", e);
//        } finally {
//            logger.info("RecevMsgServiceImpl recevMsg result = {}", result);
//        }
//    }
//
//    @KafkaListener(id = "consumerId6", topics = "#{topicName}", groupId = "#{topicGroupId}")
//    public void recevMsg6(String value) {
//        logger.info("RecevMsgServiceImpl recevMsg message = {}", value);
//        boolean result = false;
//        try {
//            result = this.processMessage(value);
//        } catch (Exception e) {
//            logger.error("RecevMsgServiceImpl recevMsg error: ", e);
//        } finally {
//            logger.info("RecevMsgServiceImpl recevMsg result = {}", result);
//        }
//    }
//
//    @KafkaListener(id = "consumerId7", topics = "#{topicName}", groupId = "#{topicGroupId}")
//    public void recevMsg7(String value) {
//        logger.info("RecevMsgServiceImpl recevMsg message = {}", value);
//        boolean result = false;
//        try {
//            result = this.processMessage(value);
//        } catch (Exception e) {
//            logger.error("RecevMsgServiceImpl recevMsg error: ", e);
//        } finally {
//            logger.info("RecevMsgServiceImpl recevMsg result = {}", result);
//        }
//    }
//
//    @KafkaListener(id = "consumerId8", topics = "#{topicName}", groupId = "#{topicGroupId}")
//    public void recevMsg8(String value) {
//        logger.info("RecevMsgServiceImpl recevMsg message = {}", value);
//        boolean result = false;
//        try {
//            result = this.processMessage(value);
//        } catch (Exception e) {
//            logger.error("RecevMsgServiceImpl recevMsg error: ", e);
//        } finally {
//            logger.info("RecevMsgServiceImpl recevMsg result = {}", result);
//        }
//    }
//
//    @KafkaListener(id = "consumerId9", topics = "#{topicName}", groupId = "#{topicGroupId}")
//    public void recevMsg9(String value) {
//        logger.info("RecevMsgServiceImpl recevMsg message = {}", value);
//        boolean result = false;
//        try {
//            result = this.processMessage(value);
//        } catch (Exception e) {
//            logger.error("RecevMsgServiceImpl recevMsg error: ", e);
//        } finally {
//            logger.info("RecevMsgServiceImpl recevMsg result = {}", result);
//        }
//    }
//
//    @KafkaListener(id = "consumerId10", topics = "#{topicName}", groupId = "#{topicGroupId}")
//    public void recevMsg10(String value) {
//        logger.info("RecevMsgServiceImpl recevMsg message = {}", value);
//        boolean result = false;
//        try {
//            result = this.processMessage(value);
//        } catch (Exception e) {
//            logger.error("RecevMsgServiceImpl recevMsg error: ", e);
//        } finally {
//            logger.info("RecevMsgServiceImpl recevMsg result = {}", result);
//        }
//    }


    /**
     * 监听topic->im-message,批量消费
     */
    @KafkaListener(id = "batchConsumerId1", topics = "#{topicName}", groupId = "#{topicGroupId}", containerFactory = "batchFactory")
    public void batchRecMsg1(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        logger.info("batchRecMsg1 record size = {}", records.size());
        batchConsumer(records);
        ack.acknowledge();
    }

    /**
     * 监听topic->im-message,批量消费
     */
    @KafkaListener(id = "batchConsumerId2", topics = "#{topicName}", groupId = "#{topicGroupId}", containerFactory = "batchFactory")
    public void batchRecMsg2(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        logger.info("batchRecMsg2 record size = {}", records.size());
        batchConsumer(records);
        ack.acknowledge();
    }

    /**
     * 监听topic->im-message,批量消费
     */
    @KafkaListener(id = "batchConsumerId3", topics = "#{topicName}", groupId = "#{topicGroupId}", containerFactory = "batchFactory")
    public void batchRecMsg3(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        logger.info("batchRecMsg3 record size = {}", records.size());
        batchConsumer(records);
        ack.acknowledge();
    }

    /**
     * 监听topic->im-message,批量消费
     */
    @KafkaListener(id = "batchConsumerId4", topics = "#{topicName}", groupId = "#{topicGroupId}", containerFactory = "batchFactory")
    public void batchRecMsg4(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        logger.info("batchRecMsg4 record size = {}", records.size());
        batchConsumer(records);
        ack.acknowledge();
    }

    /**
     * 监听topic->im-message,批量消费
     */
    @KafkaListener(id = "batchConsumerId5", topics = "#{topicName}", groupId = "#{topicGroupId}", containerFactory = "batchFactory")
    public void batchRecMsg5(List<ConsumerRecord<String, String>> records, Acknowledgment ack) {
        logger.info("batchRecMsg5 record size = {}", records.size());
        batchConsumer(records);
        ack.acknowledge();
    }

//    /**
//     * 监听topic->im-message,批量消费
//     */
//    @KafkaListener(id = "batchConsumerId6", topics = "#{topicName}", groupId = "#{topicGroupId}", containerFactory = "batchFactory")
//    public void batchRecMsg6(List<ConsumerRecord<String, String>> records) {
//        logger.info("batchRecMsg5 record size = {}", records.size());
//        batchConsumer(records);
//    }
//
//    /**
//     * 监听topic->im-message,批量消费
//     */
//    @KafkaListener(id = "batchConsumerId7", topics = "#{topicName}", groupId = "#{topicGroupId}", containerFactory = "batchFactory")
//    public void batchRecMsg7(List<ConsumerRecord<String, String>> records) {
//        logger.info("batchRecMsg5 record size = {}", records.size());
//        batchConsumer(records);
//    }
//
//    /**
//     * 监听topic->im-message,批量消费
//     */
//    @KafkaListener(id = "batchConsumerId8", topics = "#{topicName}", groupId = "#{topicGroupId}", containerFactory = "batchFactory")
//    public void batchRecMsg8(List<ConsumerRecord<String, String>> records) {
//        logger.info("batchRecMsg5 record size = {}", records.size());
//        batchConsumer(records);
//    }
//
//    /**
//     * 监听topic->im-message,批量消费
//     */
//    @KafkaListener(id = "batchConsumerId9", topics = "#{topicName}", groupId = "#{topicGroupId}", containerFactory = "batchFactory")
//    public void batchRecMsg9(List<ConsumerRecord<String, String>> records) {
//        logger.info("batchRecMsg5 record size = {}", records.size());
//        batchConsumer(records);
//    }
//
//    /**
//     * 监听topic->im-message,批量消费
//     */
//    @KafkaListener(id = "batchConsumerId10", topics = "#{topicName}", groupId = "#{topicGroupId}", containerFactory = "batchFactory")
//    public void batchRecMsg10(List<ConsumerRecord<String, String>> records) {
//        logger.info("batchRecMsg5 record size = {}", records.size());
//        batchConsumer(records);
//    }

    /**
     * 单条消费
     */
    public void consumer(ConsumerRecord<String, String> record) {
        logger.info("topic = {}, partition = {}, offset = {}, timestampType = {}, timestamp = {}, key = {}, message = {}, ", new Object[]{record.topic(), record.partition(), record.offset(), record.timestampType(), record.timestamp(), record.key(), record.value()});
        this.processMessage(record.value());
    }

    /**
     * 批量消费
     */
    public void batchConsumer(List<ConsumerRecord<String, String>> records) {
        records.forEach(record -> consumer(record));
    }

    @Override
    public boolean processMessage(String message) {
        if (StringUtils.isEmpty(message) || !JsonUtils.isJson(message))
            return false;
        boolean result = true;
        try {
            message = new String(message.getBytes(), "UTF-8");
            JSONObject map = JSONObject.parseObject(message);
            String id = String.valueOf(map.get("id"));
            String name = String.valueOf(map.get("name"));
            logger.info("RecevMsgServiceImpl processMessage success : id = {}, name = {}", new Object[]{id, name});
        } catch (Exception e) {
            logger.error("processMessage error = {}, message = {}", e, message);
            result = false;
        } finally {
            return result;
        }
    }
}
