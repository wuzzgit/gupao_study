package com.wuzz.study;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 限流策略注解方式实现
 * @author wuzongzhao
 * @date 2020/11/2 10:21
 */
@RestController
public class SentinelController {


    @SentinelResource(value = "sayHello") //针对方法级别的限流
    @GetMapping("/hello")
    public String sayHello(){
        System.out.println("hello world");
        return "hello world";
    }
}
