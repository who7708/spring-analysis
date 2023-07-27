package org.spring.boot.test.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

// 单向模式消息
public class TestOnewayProducer {
    public static void main(String[] args) throws Exception {
        // 初始化一个producer并设置Producer group name
        DefaultMQProducer producer = new DefaultMQProducer(MQConstant.TEST_ONEWAY_PRODUCER_GROUP);
        // 设置NameServer地址
        producer.setNamesrvAddr(MQConstant.TEST_ADDRESS);
        // 启动producer
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建一条消息，并指定topic、tag、body等信息，tag可以理解成标签，对消息进行再归类，RocketMQ可以在消费端对tag进行过滤
            Message msg = new Message(MQConstant.TEST_TOPIC /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 由于在oneway方式发送消息时没有请求应答处理，如果出现消息发送失败，则会因为没有重试而导致数据丢失。若数据不可丢，建议选用可靠同步或可靠异步发送方式。
            producer.sendOneway(msg);
        }
        // 一旦producer不再使用，关闭producer
        producer.shutdown();
    }
}