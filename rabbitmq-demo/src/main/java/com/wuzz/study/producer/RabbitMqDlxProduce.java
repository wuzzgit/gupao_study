package com.wuzz.study.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 死信队列
 * @author wuzongzhao
 * @date 2020/11/17 19:57
 */
@Component
public class RabbitMqDlxProduce {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void Test2(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("boot_topic_exchange", "", "什么鬼");
        }
    }
}
