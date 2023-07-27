package org.spring.boot.test.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

// 顺序消息
public class TestSeqProducer {
    public static void main(String[] args) throws Exception {
        try {
            DefaultMQProducer producer = new DefaultMQProducer(MQConstant.TEST_SEQ_PRODUCER_GROUP);
            producer.setNamesrvAddr(MQConstant.TEST_ADDRESS);
            producer.start();

            String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
            for (int i = 0; i < 100; i++) {
                int orderId = i % 10;
                Message msg =
                        new Message(MQConstant.TEST_TOPIC, tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);

                System.out.printf("%s%n", sendResult);
            }

            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}