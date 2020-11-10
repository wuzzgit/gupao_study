package com.wuzz.study;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuzongzhao
 * @date 2020/11/7 17:11
 */
@RestController
public class HelloController {

    @Reference
    private SentinelService sentinelService;


    @GetMapping("/hello")
    public String sayHello(){
        RpcContext.getContext().setAttachment("dubboApplication","sentinel-web");
        return sentinelService.sayHello();
    }

    @GetMapping("/hello2")
    public String hello2(){
        System.out.println(">>>>>>开始");
        return sentinelService.sayHello();
    }


    @GetMapping("/hello1")
    public String sayHello1(){
        return sentinelService.say();
    }
}
