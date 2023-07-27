package org.spring.boot.test.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.List;

// Push 模式消费
public class TestPushConsumer {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        // 初始化consumer，并设置consumer group name
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MQConstant.TEST_SYNC_PRODUCER_GROUP);

        // 设置NameServer地址
        consumer.setNamesrvAddr(MQConstant.TEST_ADDRESS);
        //订阅一个或多个topic，并指定tag过滤条件，这里指定*表示接收所有tag的消息
        consumer.subscribe(MQConstant.TEST_TOPIC, "*");
        //注册回调接口来处理从Broker中收到的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext context) {
                for (MessageExt messageExt : messageExtList) {
                    System.out.printf("%-50s => Message %-30s %n", Thread.currentThread().getName(), new String(messageExt.getBody(), StandardCharsets.UTF_8));
                }
                // 返回消息消费状态，ConsumeConcurrentlyStatus.CONSUME_SUCCESS为消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动Consumer
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}