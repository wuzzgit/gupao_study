package com.wuzz.study;

import com.wuzz.study.Consumer.RabbitMqLimitConsumer;
import com.wuzz.study.producer.MqLimitProduce;
import com.wuzz.study.producer.RabbitMqConfirmProduce;
import com.wuzz.study.producer.RabbitMqConfirmProduce2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuzongzhao
 * @date 2020/11/17 16:23
 */
@RestController
public class TestController {

    @Autowired
    private RabbitMqConfirmProduce rabbitMqProduce;

    @Autowired
    private MqLimitProduce mqLimitProduce;

    @Autowired
    private RabbitMqConfirmProduce2 mqLimitProduce2;

    @GetMapping("/testMqComfire")
    public void test() throws Exception{
        rabbitMqProduce.send();
    }

    @GetMapping("/testMqComfire2")
    public void testMqComfire2(){
        mqLimitProduce2.send();
    }

    @GetMapping("/limitMq")
    public void limitMq(){
        mqLimitProduce.send();
    }
}
