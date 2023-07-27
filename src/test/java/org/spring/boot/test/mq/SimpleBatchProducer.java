package org.spring.boot.test.mq;

import com.google.common.base.Splitter;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

// 批量发送消息
// 批量消息的大小不能超过 1MiB（否则需要自行分割），其次同一批 batch 中 topic 必须相同。
public class SimpleBatchProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(MQConstant.BATCH_PRODUCER_GROUP_NAME_GROUP);
        producer.setNamesrvAddr(MQConstant.TEST_ADDRESS);
        producer.start();

        //If you just send messages of no more than 1MiB at a time, it is easy to use batch
        //Messages of the same batch should have: same topic, same waitStoreMsgOK and no schedule support
        String topic = MQConstant.TEST_TOPIC;
        List<Message> messages = new ArrayList<>();
        // messages.add(new Message(topic, "Tag", "OrderID001", "Hello world 0".getBytes()));
        // messages.add(new Message(topic, "Tag", "OrderID002", "Hello world 1".getBytes()));
        // messages.add(new Message(topic, "Tag", "OrderID003", "Hello world 2".getBytes()));
        for (int i = 10; i < 100; i++) {
            messages.add(new Message(topic, "Tag", "OrderID00" + i, ("Hello world " + i).getBytes()));
        }

        SendResult sendResult = producer.send(messages);
        // msgId1,msgId2,msgId3.....
        String msgIds = sendResult.getMsgId();
        Splitter.on(",")
                .split(msgIds)
                .forEach(System.out::println);
        // System.out.println(sendResult.getMsgId());
    }
}