package com.wuzz.study.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * ack手动确认模式
 * @author wuzongzhao
 * @date 2020/11/20 15:44
 */
@Component
public class AckMqProduce implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{

    private static Logger log = LoggerFactory.getLogger(AckMqProduce.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("---- confirm ----ack="+ack+"  cause="+String.valueOf(cause));
        log.info("correlationData -->"+correlationData.toString());
        if(ack){
            log.info("---- confirm ----ack==true  cause="+cause);
        }else{
            log.info("---- confirm ----ack==false  cause="+cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("-------》》交换机匹配不到队列返回《《-------------");
    }

    public void send(){
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("ackTwo_exchange","","测试一下ACK手动确认模式",correlationData);
    }

    public void send2(){
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("ackTwo_exchange_return","","测试return模式",correlationData);
    }
}
