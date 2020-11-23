package com.wuzz.study;

import com.wuzz.study.producer.AckMqProduce;
import com.wuzz.study.producer.RabbitMqConfirmProduce;
import com.wuzz.study.producer.RabbitMqConfirmProduce2;
import com.wuzz.study.producer.RabbitMqDlxProduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuzongzhao
 * @date 2020/11/17 16:23
 */
@RestController
public class TestController {

    @Autowired
    private RabbitMqConfirmProduce rabbitMqProduce;


    @Autowired
    private RabbitMqConfirmProduce2 mqLimitProduce2;

    @Autowired
    private AckMqProduce ackMqProduce;

    @Autowired
    private RabbitMqDlxProduce rabbitMqDlxProduce;

    @GetMapping("/testMqComfire")
    public void test() throws Exception{
        rabbitMqProduce.send();
    }

    @GetMapping("/testMqComfire2")
    public void testMqComfire2(){
        mqLimitProduce2.send();
    }


    @GetMapping("/ackMqProduce")
    public void ackMqProduce(){
        ackMqProduce.send();
    }

    @GetMapping("/ackDirtExchangeMqProduce")
    public void ackDirtExchangeMqProduce(){
        ackMqProduce.send2();
    }

    @GetMapping("/rabbitMqDlxProduce")
    public void rabbitMqDlxProduce()throws Exception{
        rabbitMqDlxProduce.Test2();
    }

    @PostMapping("/postCs")
    @ResponseBody
    public String postCs(HttpServletRequest httpServletRequest){
        String st=MD5Utils.getPostData(httpServletRequest);
        System.out.println(st);
        return st;
    }
}
