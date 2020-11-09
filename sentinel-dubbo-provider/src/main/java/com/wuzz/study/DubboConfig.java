package com.wuzz.study;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * dubbo相关配置
 * @author wuzongzhao
 * @date 2020/11/7 15:37
 */
@Configuration
@DubboComponentScan("com.wuzz.study")
public class DubboConfig {

    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig=new ApplicationConfig();
        applicationConfig.setName("sentinel-dubbo");
        applicationConfig.setOwner("wzz");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig(){
      RegistryConfig registryConfig=new RegistryConfig();
      registryConfig.setAddress("zookeeper://127.0.0.1:2181");
      return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig=new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;
    }
}
