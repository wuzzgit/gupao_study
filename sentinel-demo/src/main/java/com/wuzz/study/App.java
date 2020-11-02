package com.wuzz.study;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * sentinel限流demo学习
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        initRule();
        System.out.println("Hello World!");
        SpringApplication.run(App.class, args);
    }

    /**
     * 初始化限流规则
     */
    private static void initRule() {
        List<FlowRule> ruleList = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("sayHello");//阈值接口或者资源名（方法名）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的阈值的类型
        flowRule.setCount(10);//QPS 10
        ruleList.add(flowRule);
        FlowRuleManager.loadRules(ruleList);
    }



}
