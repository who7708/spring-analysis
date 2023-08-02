package org.spring.boot.test.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-08-02
 */
public class TestKafka {
    @Test
    public void testProducer() throws Exception {
        System.out.println("===== testProducer =====");
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.5:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("buffer.memory", 67108864);
        props.put("batch.size", 131072);
        props.put("linger.ms", 100);
        props.put("max.request.size", 10485760);
        props.put("acks", "1");
        props.put("retries", 10);
        props.put("retry.backoff.ms", 500);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 100; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("quickstart-events", 3, "key_" + (i + 1), "val_" + (i + 1));
            producer.send(record).get(200, TimeUnit.MILLISECONDS);
        }
        System.out.println("finished...");
    }

    @Test
    public void testConsumer() {
        System.out.println("===== testConsumer =====");
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.5:9092");  // 指定 Broker
        properties.put("group.id", "experiment");              // 指定消费组群 ID
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // 将 key 的字节数组转成 Java 对象
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  // 将 value 的字节数组转成 Java 对象

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton("quickstart-events"));  // 订阅主题 order-events
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                String info = String.format("[Topic: %s][Partition:%d][Offset:%d][Key:%s][Message:%s]",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value());
                System.out.println("Received:" + info);
            }
        }
    }

    // /**
    //  * 同步发送消息
    //  */
    // public <T> void send(ProducerRecord<T, T> record) {
    //     try {
    //         producer.send(record).get(200, TimeUnit.MILLISECONDS);
    //     } catch (Exception ex) {
    //         log.error(ex.getMessage(), ex);
    //     }
    //
    // }
    //
    // /**
    //  * 异步发送消息
    //  */
    // public <T> void sendAsync(ProducerRecord<T, T> record, Callback callback) {
    //     try {
    //         producer.send(record, callback);
    //     } catch (Exception ex) {
    //         log.error(ex.getMessage(), ex);
    //     }
    //
    // }
}
