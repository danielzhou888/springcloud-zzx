package com.zzx.rocketmq.zzxrocketmq.client;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class ClientDemo1 {

    class Consumer {
        public void consume() {
            DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("zzx-consumer");
            pushConsumer.setNamesrvAddr("39.105.176.53:9876");
            try {
                pushConsumer.subscribe("topic1", "tag1 || tag2 || tag3 ");
            } catch (MQClientException e) {
                e.printStackTrace();
            }
            pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            pushConsumer.setMessageListener(new MessageListenerConcurrently() {
                Random random = new Random();
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                    ConsumeConcurrentlyContext context) {
                    System.out.println(Thread.currentThread().getId() + " Receive Consumer Message ...");
                    msgs.forEach(m -> {
                        System.out.println(m + ", content : " + new String(m.getBody()));
                    });
                    try {
                        Thread.sleep(random.nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            try {
                pushConsumer.start();
            } catch (MQClientException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer start ...");
        }
    }

    static class Productor {
        public void produce() {
            DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
            producer.setNamesrvAddr("39.105.176.53:9876");
            try {
                // producer.createTopic("topic1", "topic1", 6);
                producer.start();
                String[] tags = new String[]{"tag1", "tag2", "tag3"};
                List<OrderDemo> orders = new Productor().buildOrders();
                for (int i = 0; i < 10; i++) {
                    Date date = new Date();
                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    String body = time + "-Hello, I'm Producer-" + orders.get(i);
                    Message message = new Message("topic1", tags[i % tags.length], "KEY" + i, body.getBytes());
                    SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                        @Override
                        public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                            Long id = (Long)arg;
                            long index = id % mqs.size();
                            return mqs.get((int)index);
                        }
                    }, orders.get(i).getOrderId());
                    System.out.println(sendResult + ", body:" + body);
                }
                producer.shutdown();
            } catch (MQClientException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQBrokerException e) {
                e.printStackTrace();
            }
        }

        /**
         * 生成模拟订单数据
         */
        private List<OrderDemo> buildOrders() {
            List<OrderDemo> orderList = new ArrayList<OrderDemo>();

            OrderDemo orderDemo = new OrderDemo();
            orderDemo.setOrderId(15103111039L);
            orderDemo.setDesc("创建");
            orderList.add(orderDemo);

            orderDemo = new OrderDemo();
            orderDemo.setOrderId(15103111065L);
            orderDemo.setDesc("创建");
            orderList.add(orderDemo);

            orderDemo = new OrderDemo();
            orderDemo.setOrderId(15103111039L);
            orderDemo.setDesc("付款");
            orderList.add(orderDemo);

            orderDemo = new OrderDemo();
            orderDemo.setOrderId(15103117235L);
            orderDemo.setDesc("创建");
            orderList.add(orderDemo);

            orderDemo = new OrderDemo();
            orderDemo.setOrderId(15103111065L);
            orderDemo.setDesc("付款");
            orderList.add(orderDemo);

            orderDemo = new OrderDemo();
            orderDemo.setOrderId(15103117235L);
            orderDemo.setDesc("付款");
            orderList.add(orderDemo);

            orderDemo = new OrderDemo();
            orderDemo.setOrderId(15103111065L);
            orderDemo.setDesc("完成");
            orderList.add(orderDemo);

            orderDemo = new OrderDemo();
            orderDemo.setOrderId(15103111039L);
            orderDemo.setDesc("推送");
            orderList.add(orderDemo);

            orderDemo = new OrderDemo();
            orderDemo.setOrderId(15103117235L);
            orderDemo.setDesc("完成");
            orderList.add(orderDemo);

            orderDemo = new OrderDemo();
            orderDemo.setOrderId(15103111039L);
            orderDemo.setDesc("完成");
            orderList.add(orderDemo);

            return orderList;
        }
    }

    @Data static
    class OrderDemo {
        private Long orderId;
        private String desc;
    }

    public static void main(String[] args) {
        Productor productor = new Productor();
        productor.produce();
    }

}
