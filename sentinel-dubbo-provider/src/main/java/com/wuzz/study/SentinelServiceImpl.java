package com.wuzz.study;

import org.apache.dubbo.config.annotation.Service;

/**
 * @author wuzongzhao
 * @date 2020/11/7 15:52
 */
@Service
public class SentinelServiceImpl implements SentinelService {

    @Override
    public String sayHello() {
        System.out.println(">>>>.....进入了限流A！.....<<<<");
        return "hello,已经进入了限流！！！";
    }

    @Override
    public String say() {
        System.out.println(">>>>.....进入了限流B！.....<<<<");
        return "hello,已经进入了限流！！！";
    }
}
