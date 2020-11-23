package com.wuzz.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * rabbitMq demo实现
 * Hello world!
 * 1、私信队列实现 OK
 * 2、ack手动确认机制实现 OK
 * 3、当消息无法匹配到队列时，会发回给生产者 return模式 OK
 * 4、通过TTL测试死信队列 OK
 * 5、面试题总结
 */
@SpringBootApplication
public class RabbitMqApp {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApp.class, args);
        System.out.println("Hello World!");
    }
}
