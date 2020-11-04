package com.yj.base.producer;

// 生产者

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 1.创建消息生产者producer，并制定生产者组名
 * 2.指定Nameserver地址
 * 3.启动producer
 * 4.创建消息对象，指定主题Topic、Tag和消息体
 * 5.发送消息
 * 6.关闭生产者producer
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("group1");

        producer.setNamesrvAddr("192.168.25.100:9876;192.168.25.101:9876");

        producer.start();

        for (int i = 0; i <10 ; i++) {
            Message message = new Message("base","tag",("hello"+i).getBytes());
            SendResult result = producer.send(message);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("发送结果："+result);
        }

        producer.shutdown();
    }


}
