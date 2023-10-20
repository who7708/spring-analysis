package org.spring.boot.test.mq.rocketmq;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;
import java.util.List;

// LitePull 消费，Subscribe 模式
public class TestLitePullConsumerSubscribe {
    public static volatile boolean running = true;

    public static void main(String[] args) throws Exception {
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer(MQConstant.TEST_SYNC_PRODUCER_GROUP);
        consumer.setNamesrvAddr(MQConstant.TEST_ADDRESS);
        consumer.subscribe(MQConstant.TEST_TOPIC, "*");
        consumer.setPullBatchSize(20);
        consumer.start();
        try {
            while (running) {
                // 默认5秒（即 pollTimeoutMillis）执行一次poll，所以可以使用while
                List<MessageExt> messageExtList = consumer.poll();
                System.out.println(DateFormatUtils.format(System.currentTimeMillis(), "HH:mm:ss.SSS"));
                for (MessageExt messageExt : messageExtList) {
                    System.out.printf("%-10s => Message %-10s %n", Thread.currentThread().getName(), new String(messageExt.getBody(), StandardCharsets.UTF_8));
                }
            }
        } finally {
            consumer.shutdown();
        }
    }
}