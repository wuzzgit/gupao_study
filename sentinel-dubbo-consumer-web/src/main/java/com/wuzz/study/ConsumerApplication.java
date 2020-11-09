package com.wuzz.study;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * sentinel dubbo consumer
 * Hello world!
 */
@SpringBootApplication
@DubboComponentScan
public class ConsumerApplication {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
