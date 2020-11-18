package com.wuzz.study.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mq消息限流的模式
 * @author wuzongzhao
 * @date 2020/11/17 16:53
 */
@Component
public class MqLimitProduce {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        System.out.println(">>>>>>发送成功");
        rabbitTemplate.convertAndSend("Fanout_EXCHANGE","","测试一下广播消息设置限流");
    }

}
