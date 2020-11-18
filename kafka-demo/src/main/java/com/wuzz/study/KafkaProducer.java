package com.wuzz.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产者发送消息
 * @author wuzongzhao
 * @date 2020/11/14 15:45
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    public void producerSend(){
        //addCallBack添加回调
        kafkaTemplate.send("cskafka","12345","12345789").addCallback(success->{
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        },failure->{
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }
}
