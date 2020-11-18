package com.wuzz.study;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * mqconfig配置文件
 * @author wuzongzhao
 * @date 2020/11/17 15:55
 */
@Configuration
public class RabbitmqConfig {
    // 交换机
    @Bean("topicExchange")
    public TopicExchange getTopicExchange(){
        return new TopicExchange("TOPIC_EXCHANGE");
    }

    //绑定通道
    @Bean("topicQueue")
    public Queue getSecondQueue(){
        return new Queue("topicQueue");
    }

    @Bean("fanoutExchange")
    public TopicExchange getFanoutExchange(){
        return new TopicExchange("Fanout_EXCHANGE");
    }
    //绑定通道
    @Bean("fanoutQueue")
    public Queue getFanoutQueue(){
        return new Queue("fanoutQueue");
    }

    @Bean
    public Binding confirmExchange(@Qualifier("topicExchange") Exchange exchange, @Qualifier("topicQueue") Queue queue){
        //bind(queue) 绑定队列 to(exchange) 绑定交换机 with("") routingKey这里绑定的是空白可以按照自己的要求绑定  noargs() 没有参数
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    @Bean
    public Binding fanoutExchange(@Qualifier("fanoutExchange") Exchange exchange, @Qualifier("fanoutQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    //--------------------------------死信队列设置开始-----------------------------------------------------
    /*
     * 死信交换机名称
     */
    public static final String DLX_EXCHANGE_NAME="dlx_exchange";
    /**
     * 死信队列名称
     */
    public static final String DLX_QUEUE_NAME="dlx_queue";
    /**
     * 死信ROUTING_KEY名称
     */
    public static final String DLX_ROUTING_KEY="dlx";

    /**
     *
     * @return 死信交换机
     */
    @Bean("dlx_exchange")
    Exchange dlxExchange(){
        return ExchangeBuilder.topicExchange(DLX_EXCHANGE_NAME).durable(true).build();
    }

    /**
     *
     * @return 死信队列
     */
    @Bean("dlx_queue")
    Queue dlxQueue(){
        return QueueBuilder.durable(DLX_QUEUE_NAME).build();
    }

    /**
     *
     * @param exchange 死信交换机
     * @param queue 死信队列
     * @return  Binding 死信对象
     */
    @Bean
    Binding dlxBinding(@Qualifier("dlx_exchange") Exchange exchange,@Qualifier("dlx_queue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(DLX_ROUTING_KEY).noargs();
    }


    @Bean("boot_queue")
    Queue bootQueue(){
        Map<String, Object> hashMap = new HashMap<>(10);
        //绑定死信交换机
        hashMap.put("x-dead-letter-exchange",DLX_EXCHANGE_NAME);
        //绑定死信交换机路由key
        hashMap.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
        //设置队列消息超时时间
        hashMap.put("x-message-ttl",10000);
        hashMap.put("x-max-length", 10);
        return QueueBuilder.durable("boot_queue").withArguments(hashMap).build();
    }

    /**
     *
     * @return 普通交换机
     */
    @Bean("boot_topic_exchange")
    Exchange bootExchange(){
        return ExchangeBuilder.topicExchange("boot_topic_exchange").durable(true).build();
    }

    /**
     *
     * @param exchange 交换机
     * @param queue 队列
     * @return  Binding 对象
     */
    @Bean
    Binding bootBinding(@Qualifier("boot_topic_exchange") Exchange exchange,@Qualifier("boot_queue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }

    /**
     * 很重要
     * RabbitTemplate改为多列这样每个消息的comfire互相不干扰 不然会报错
     * @param connectionFactory
     * @return
     */
    @Bean
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setMessageConverter(new SerializerMessageConverter());
        return template;
    }

}
