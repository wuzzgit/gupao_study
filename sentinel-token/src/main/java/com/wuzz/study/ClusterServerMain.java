package com.wuzz.study;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

import java.util.Collections;

/**
 * @author wuzongzhao
 * @date 2020/11/10 19:54
 */
public class ClusterServerMain {
        public static void main(String[] args) throws Exception {
            ClusterTokenServer tokenServer=new SentinelDefaultTokenServer();
            ClusterServerConfigManager.loadGlobalTransportConfig(
                    new ServerTransportConfig().setIdleSeconds(600).setPort(9999));
            ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("sentinel-token")); //设置成动态
            tokenServer.start();
        }
}
