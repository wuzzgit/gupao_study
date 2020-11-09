package com.wuzz.study;

import org.apache.dubbo.config.annotation.Reference;
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
        return sentinelService.sayHello();
    }
}
