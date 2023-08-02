package org.spring.boot.test.mq.rocketmq;

import org.apache.commons.lang3.RandomUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.HashMap;
import java.util.Map;

// 延迟消息,共18个等级
public class TestScheduledMessageProducer {
    private static final Map<Integer, String> DelayLevelAndTimeMap = new HashMap<>();

    static {
        DelayLevelAndTimeMap.put(1, "1s");
        DelayLevelAndTimeMap.put(2, "5s");
        DelayLevelAndTimeMap.put(3, "10s");
        DelayLevelAndTimeMap.put(4, "30s");
        DelayLevelAndTimeMap.put(5, "1min");
        DelayLevelAndTimeMap.put(6, "2min");
        DelayLevelAndTimeMap.put(7, "3min");
        DelayLevelAndTimeMap.put(8, "4min");
        DelayLevelAndTimeMap.put(9, "5min");
        DelayLevelAndTimeMap.put(10, "6min");
        DelayLevelAndTimeMap.put(11, "7min");
        DelayLevelAndTimeMap.put(12, "8min");
        DelayLevelAndTimeMap.put(13, "9min");
        DelayLevelAndTimeMap.put(14, "10min");
        DelayLevelAndTimeMap.put(15, "20s");
        DelayLevelAndTimeMap.put(16, "30s");
        DelayLevelAndTimeMap.put(17, "1h");
        DelayLevelAndTimeMap.put(18, "2h");
    }

    public static void main(String[] args) throws Exception {
        // Instantiate a producer to send scheduled messages
        DefaultMQProducer producer = new DefaultMQProducer(MQConstant.TEST_SCHEDULED_MESSAGE_PRODUCER_GROUP);
        producer.setNamesrvAddr(MQConstant.TEST_ADDRESS);
        // Launch producer
        producer.start();
        int totalMessagesToSend = 100;
        for (int i = 0; i < totalMessagesToSend; i++) {
            // [1,18] 随机1-18等级
            int delayTimeLevel = RandomUtils.nextInt(1, 19);
            Message message = new Message("TestTopic", ("Hello scheduled message " + i).getBytes());
            // This message will be delivered to consumer 10 seconds later.
            message.setDelayTimeLevel(delayTimeLevel);
            // Send the message
            SendResult sendResult = producer.send(message);
            System.out.printf("level %-3d => delay %-5s, msgId %s %n", delayTimeLevel, DelayLevelAndTimeMap.get(delayTimeLevel), sendResult.getMsgId());
        }

        // Shutdown producer after use.
        producer.shutdown();
    }

}