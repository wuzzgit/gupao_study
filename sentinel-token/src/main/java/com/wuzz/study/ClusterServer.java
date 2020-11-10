package com.wuzz.study;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author wuzongzhao
 * @date 2020/11/10 16:32
 */
@Component
@Order(value = 1)
public class ClusterServer implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClusterTokenServer tokenServer = new SentinelDefaultTokenServer();
        ClusterServerConfigManager.loadGlobalTransportConfig(new ServerTransportConfig().setIdleSeconds(600).setPort(8085));
        ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("sentinel-token"));
        tokenServer.start();
    }
}
