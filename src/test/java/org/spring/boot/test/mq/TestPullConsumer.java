package org.spring.boot.test.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.PullStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.util.Set;

// Pull 模式消费
public class TestPullConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(MQConstant.TEST_SYNC_PRODUCER_GROUP);
        // DefaultLitePullConsumer consumer = new DefaultLitePullConsumer(MQConstant.TEST_SYNC_PRODUCER_GROUP);
        consumer.setNamesrvAddr(MQConstant.TEST_ADDRESS);
        consumer.start();
        try {
            Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues(MQConstant.TEST_TOPIC);
            for (MessageQueue messageQueue : messageQueues) {
                System.out.printf("%-10s, %-10s, %-10s %n", messageQueue.getQueueId(), messageQueue.getBrokerName(), messageQueue.getTopic());

                MessageQueue mq = new MessageQueue();
                mq.setQueueId(messageQueue.getQueueId());
                mq.setTopic(messageQueue.getTopic());
                mq.setBrokerName(messageQueue.getBrokerName());

                long offset = 0;
                PullResult pullResult = consumer.pull(mq, "*", offset, 32);
                if (pullResult.getPullStatus().equals(PullStatus.FOUND)) {
                    for (MessageExt messageExt : pullResult.getMsgFoundList()) {
                        System.out.printf("%-10s => Message %-10s %n", Thread.currentThread().getName(), new String(messageExt.getBody(), StandardCharsets.UTF_8));
                    }
                    consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        consumer.shutdown();
    }
}