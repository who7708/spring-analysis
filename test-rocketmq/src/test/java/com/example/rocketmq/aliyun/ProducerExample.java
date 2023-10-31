package com.example.rocketmq.aliyun;

import org.apache.rocketmq.client.apis.*;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;

public class ProducerExample {
    public static void main(String[] args) throws ClientException {
        StaticSessionCredentialsProvider staticSessionCredentialsProvider =
                new StaticSessionCredentialsProvider("****", "****"); //在RocketMQ实例详情页面获取用户名和密码
        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder()
                .setEndpoints("rmq-cn-xxx.{regionId}.rmq.aliyuncs.com:8080")  //接入点
                .setCredentialProvider(staticSessionCredentialsProvider)
                .build();

        /**
         * 实例接入点，从控制台实例详情页的接入点页签中获取。
         * 如果是在阿里云ECS内网访问，建议填写VPC接入点。
         * 如果是在本地公网访问，或者是线下IDC环境访问，可以使用公网接入点。使用公网接入点访问，必须开启实例的公网访问功能。
         */
        String endpoints = "rmq-cn-xxx.{regionId}.rmq.aliyuncs.com:8080";
        //消息发送的目标Topic名称，需要提前在控制台创建，如果不创建直接使用会返回报错。
        String topic = "topic_normal";
        ClientServiceProvider provider = ClientServiceProvider.loadService();
        ClientConfigurationBuilder builder = ClientConfiguration.newBuilder().setEndpoints(endpoints);
        /**
         * 如果是使用公网接入点访问，configuration还需要设置实例的用户名和密码。用户名和密码在控制台实例详情页获取。
         * 如果是在阿里云ECS内网访问，无需填写该配置，服务端会根据内网VPC信息智能获取。
         */
        builder.setCredentialProvider(new StaticSessionCredentialsProvider("****", "****"));
        ClientConfiguration configuration = builder.build();
        /**
         * 初始化Producer时直接配置需要使用的Topic列表（这个参数可以配置多个Topic），实现提前检查错误配置、拦截非法配置启动。
         * 针对非事务消息 Topic，也可以不配置，服务端会动态检查消息的Topic是否合法。
         * 注意！！！事务消息Topic必须提前配置，以免事务消息回查接口失败，具体原理请参见事务消息。
         */
        Producer producer = provider.newProducerBuilder()
                .setTopics(topic)
                // .setClientConfiguration(configuration)
                .setClientConfiguration(clientConfiguration)
                .build();
        //普通消息发送。
        Message message = provider.newMessageBuilder()
                .setTopic(topic)
                //设置消息索引键，可根据关键字精确查找某条消息。
                .setKeys("messageKey")
                //设置消息Tag，用于消费端根据指定Tag过滤消息。
                .setTag("messageTag")
                //消息体。
                .setBody("messageBody".getBytes())
                .build();
        try {
            //发送消息，需要关注发送结果，并捕获失败等异常。
            SendReceipt sendReceipt = producer.send(message);
            System.out.println(sendReceipt.getMessageId());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}