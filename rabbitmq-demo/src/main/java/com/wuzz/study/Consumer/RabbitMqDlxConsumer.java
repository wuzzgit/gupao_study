package com.wuzz.study.Consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wuzongzhao
 * @date 2020/11/17 19:59
 */
@Component
public class RabbitMqDlxConsumer {

    private static int num=2;

    @RabbitHandler
    @RabbitListener(queues = "boot_queue")
    public void process(String messages, Channel channel, Message message) throws Exception{
        System.out.println(">>>>>>>>>"+num);
        Thread.sleep(8000);
        System.out.println("死信消费者测试开始----->"+messages);

        try {
            /**
             * 确认一条消息：<br>
             * channel.basicAck(deliveryTag, false); <br>
             * deliveryTag:该消息的index <br>
             * multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息 <br>
             */
            if(num>1){
                System.out.println(">>>>>>>>>>>消息加入死信队列后,消费者会从死信队列拉取数据，次数："+num);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),
                        false, true);
                num=num+1;
            }

        } catch (Exception e) {
            //消费者处理出了问题，需要告诉队列信息消费失败
            /**
             * 拒绝确认消息:<br>
             * channel.basicNack(long deliveryTag, boolean multiple, boolean requeue) ; <br>
             * deliveryTag:该消息的index<br>
             * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。<br>
             * requeue：被拒绝的是否重新入队列 <br>
             */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),
                    false, true);
            System.err.println("get msg1 failed msg = "+messages);

            /**
             * 拒绝一条消息：<br>
             * channel.basicReject(long deliveryTag, boolean requeue);<br>
             * deliveryTag:该消息的index<br>
             * requeue：被拒绝的是否重新入队列
             */
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}