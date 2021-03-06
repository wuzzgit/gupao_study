package com.wuzz.study.producer;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 确认模式消息生产者
 * @author wuzongzhao
 * @date 2020/11/17 16:01
 */
@Component
public class RabbitMqConfirmProduce implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(b){
            System.out.println(">>>>>>接收成功"+correlationData.getId());
        }else {
            System.out.println(">>>>>>接收失败");
        }
    }

    public void send() throws Exception{
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("TOPIC_EXCHANGE","","测试一下123",correlationData);
        Thread.sleep(1000);
    }
}
