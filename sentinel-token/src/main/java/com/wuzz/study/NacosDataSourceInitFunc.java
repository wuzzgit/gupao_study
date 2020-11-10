package com.wuzz.study;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * 初始化nacos
 * @author wuzongzhao
 * @date 2020/11/10 16:46
 */
public class NacosDataSourceInitFunc implements InitFunc {

    private final String remoteAddress="localhost"; //nacos 配置中心的服务host
    private final String groupId="SENTINEL_GROUP";
    private final String FLOW_POSTFIX="-flow-rules"; //dataid（names+postfix）

    @Override
    public void init() throws Exception {
        ClusterFlowRuleManager.setPropertySupplier(namespace->{
            ReadableDataSource<String, List<FlowRule>> rds=
                    new NacosDataSource<List<FlowRule>>( remoteAddress,groupId,namespace+FLOW_POSTFIX,
                    source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>(){}));
            return rds.getProperty();
    });
    }
}
