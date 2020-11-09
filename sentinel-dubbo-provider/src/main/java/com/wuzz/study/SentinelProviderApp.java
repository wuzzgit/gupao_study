package com.wuzz.study;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Sentinel-Provider,sentinel限流dubbo整合
 * Hello world!
 */
@SpringBootApplication
public class SentinelProviderApp {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        initRule();
        SpringApplication.run(SentinelProviderApp.class, args);
    }

    /**
     * 初始化限流规则
     */
    private static void initRule() {
        //多规则设置
        List<FlowRule> ruleList = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("com.wuzz.study.SentinelService:sayHello()");//阈值接口或者资源名（方法名）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的阈值的类型
        flowRule.setCount(15);//QPS 10
        flowRule.setLimitApp("sentinel-web");//设置来源
        ruleList.add(flowRule);
/*
        FlowRule flowRule2 = new FlowRule();
        flowRule2.setResource("com.wuzz.study.SentinelService:sayHello2()");//阈值接口或者资源名（方法名）
        flowRule2.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的阈值的类型
        flowRule2.setCount(5);//QPS 10
        ruleList.add(flowRule2);*/

        FlowRuleManager.loadRules(ruleList);
    }
}
