package com.wuzz.study.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ack确认机制
 * @author wuzongzhao
 * @date 2020/11/20 15:40
 */
@Configuration
public class AckConfig {

    // 定义直连的交换机
    @Bean("ackTwo_exchange")
    public DirectExchange getDirectExchange(){
        return new DirectExchange("ackTwo_exchange");
    }


    // 定义直连的交换机
    @Bean("ackTwo_exchange_return")
    public DirectExchange getAckDirectExchange(){
        return new DirectExchange("ackTwo_exchange_return");
    }



    //绑定通道
    @Bean("ackTwo_Queue")
    public Queue getSecondQueue(){
        return new Queue("ackTwo_Queue");
    }

    //直连模式的交换机绑定通道
    @Bean
    public Binding directExchange(@Qualifier("ackTwo_exchange") Exchange exchange, @Qualifier("ackTwo_Queue") Queue queue){
        //bind(queue) 绑定队列 to(exchange) 绑定交换机 with("") routingKey这里绑定的是空白可以按照自己的要求绑定  noargs() 没有参数
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

}
