package com.wuzz.study.Consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author wuzongzhao
 * @date 2020/11/17 17:03
 */
@Component
@RabbitListener(queues = "fanoutQueue")
public class RabbitMqLimitConsumer {

    @RabbitHandler
    public void process(String message){
        System.out.println(message);
    }
}
