package org.spring.boot.test.mq.rocketmq;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// LitePull 消费，Assign 模式
public class TestLitePullConsumerAssign {
    public static volatile boolean running = true;

    public static void main(String[] args) throws Exception {
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer(MQConstant.TEST_SYNC_PRODUCER_GROUP);
        consumer.setNamesrvAddr(MQConstant.TEST_ADDRESS);
        consumer.setAutoCommit(false);
        consumer.start();
        Collection<MessageQueue> mqSet = consumer.fetchMessageQueues(MQConstant.TEST_TOPIC);
        List<MessageQueue> list = new ArrayList<>(mqSet);
        List<MessageQueue> assignList = new ArrayList<>();
        for (int i = 0; i < list.size() / 2; i++) {
            assignList.add(list.get(i));
        }
        consumer.assign(assignList);
        consumer.seek(assignList.get(0), 10);
        try {
            while (running) {
                List<MessageExt> messageExtList = consumer.poll();
                System.out.println(DateFormatUtils.format(System.currentTimeMillis(), "HH:mm:ss.SSS"));
                for (MessageExt messageExt : messageExtList) {
                    System.out.printf("%-10s => Message %-10s %n", Thread.currentThread().getName(), new String(messageExt.getBody(), StandardCharsets.UTF_8));
                }
                consumer.commitSync();
            }
        } finally {
            consumer.shutdown();
        }
    }
}