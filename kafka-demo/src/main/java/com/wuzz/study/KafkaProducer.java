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
        kafkaTemplate.send("cskafka","12345","12345789");
    }
}
