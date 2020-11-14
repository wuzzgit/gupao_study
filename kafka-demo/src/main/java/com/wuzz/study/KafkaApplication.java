package com.wuzz.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 消息中间件-----卡夫卡集成相关demo
 * Hello world!
 */
@SpringBootApplication
public class KafkaApplication {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        ConfigurableApplicationContext context= SpringApplication.run(KafkaApplication.class, args);
        KafkaProducer kp=context.getBean(KafkaProducer.class);
        for (int i = 0; i < 10; i++) {
            kp.producerSend();
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
