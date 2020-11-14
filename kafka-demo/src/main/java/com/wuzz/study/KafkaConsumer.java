package com.wuzz.study;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 消费者接收消息
 * @author wuzongzhao
 * @date 2020/11/14 15:46
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"cskafka","first_topic"})
    public void listener(ConsumerRecord record){
        Optional msg=Optional.ofNullable(record.value());
        if(msg.isPresent()){
            System.out.println(msg.get());
        }
    }
}
