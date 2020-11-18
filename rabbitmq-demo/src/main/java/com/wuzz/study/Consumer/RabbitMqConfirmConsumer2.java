package com.wuzz.study.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 确认模式消费者
 * @author wuzongzhao
 * @date 2020/11/17 16:01
 */
@Component
@RabbitListener(queues = "fanoutQueue")
public class RabbitMqConfirmConsumer2 {

    @RabbitHandler
    private void process(String message){
        System.out.println("消费者faout----->"+message);
    }

}
