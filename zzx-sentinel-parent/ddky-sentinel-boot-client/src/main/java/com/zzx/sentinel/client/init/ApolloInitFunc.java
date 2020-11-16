//package com.zzx.sentinel.client.init;
//
//import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
//import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
//import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
//import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
//import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
//import com.alibaba.csp.sentinel.datasource.apollo.ApolloDataSource;
//import com.alibaba.csp.sentinel.init.InitFunc;
//import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
//import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
//import com.alibaba.csp.sentinel.slots.system.SystemRule;
//import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
//import com.alibaba.csp.sentinel.transport.config.TransportConfig;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import com.zzx.sentinel.client.cluster.parser.ClusterAssignConfigParser;
//import com.zzx.sentinel.client.cluster.parser.ClusterAssignStateParser;
//import com.zzx.sentinel.client.config.ApolloConfigUtil;
//import com.zzx.sentinel.client.config.DdkySentinelProperties;
//
//import java.util.List;
//
///**
// * @author zhouzhixiang
// */
//public class ApolloInitFunc implements InitFunc {
//
//    private static DdkySentinelProperties ddkySentinelProperties;
//    private String tokenServerNameSpace = ApolloConfigUtil.getTokenServerNamespaceName();
//    private String defaultRules = "[]";
//    private String namespaceName;
//    private String projectName;
//    private String tokenServerNameSpaceName;
//
//    static {
//        ddkySentinelProperties = DdkySentinelProperties.getInstances();
//    }
//
//    @Override
//    public void init() throws Exception {
//        initClientRules();
//        initClusterConfig();
//    }
//
//    /**
//     * 初始化普通限流规则
//     */
//    private void initClientRules() {
//        namespaceName = ddkySentinelProperties.getNamespaceName();
//        projectName = ddkySentinelProperties.getProjectName();
//        //流控规则
//        registerFlowRuleProperty();
//        //系统规则
//        registerSystemRuleProperty();
//        //热点规则
//        registerParamFlowRuleProperty();
//        //降级规则
//        registerDegradeRuleProperty();
//        //授权规则
//        registerAuthorityRuleProperty();
//    }
//
//    /**
//     * 初始化集群限流规则
//     */
//    private void initClusterConfig() {
//        tokenServerNameSpaceName = ApolloConfigUtil.getTokenServerNamespaceName();
//        //等待transport端口分配完毕
//        while (TransportConfig.getRuntimePort() == -1) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        //为每个client设置目标token server
//        initClientServerAssignProperty();
//        //初始化token client通用超时配置
//        initClientConfigProperty();
//        //初始化客户端状态为client 或者 server
//        initStateProperty();
//
//    }
//
//    private void initStateProperty() {
//        ReadableDataSource<String, Integer> clusterModeDs = new ApolloDataSource<>(tokenServerNameSpaceName,
//                ApolloConfigUtil.getTokenServerNamespaceDataId(), defaultRules, new ClusterAssignStateParser());
//        ClusterStateManager.registerProperty(clusterModeDs.getProperty());
//    }
//
//    private void initClientServerAssignProperty() {
//        ReadableDataSource<String, ClusterClientAssignConfig> clientAssignDs = new ApolloDataSource<>(tokenServerNameSpaceName,
//                ApolloConfigUtil.getTokenServerClusterMapDataId(), defaultRules, new ClusterAssignConfigParser());
//        ClusterClientConfigManager.registerServerAssignProperty(clientAssignDs.getProperty());
//    }
//
//    private void initClientConfigProperty() {
//        ReadableDataSource<String, ClusterClientConfig> clientConfigDs = new ApolloDataSource<>(tokenServerNameSpace,
//                ApolloConfigUtil.getTokenClientConfigDataId(), defaultRules, source -> JSON.parseObject(source,
//                new TypeReference<ClusterClientConfig>() {
//        }));
//        ClusterClientConfigManager.registerClientConfigProperty(clientConfigDs.getProperty());
//    }
//
//    private void registerAuthorityRuleProperty() {
//        ReadableDataSource<String, List<AuthorityRule>> authorityRuleDataSource = new ApolloDataSource<>(namespaceName,
//                ApolloConfigUtil.getAuthorityDataId(projectName), defaultRules, source -> JSON.parseObject(source,
//                new TypeReference<List<AuthorityRule>>() {
//        }));
//        AuthorityRuleManager.register2Property(authorityRuleDataSource.getProperty());
//    }
//
//    private void registerDegradeRuleProperty() {
//        ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new ApolloDataSource<>(namespaceName,
//                ApolloConfigUtil.getDegradeDataId(projectName), defaultRules, source -> JSON.parseObject(source,
//                new TypeReference<List<DegradeRule>>() {
//        }));
//        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
//    }
//
//    private void registerParamFlowRuleProperty() {
//        ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleDataSource = new ApolloDataSource<>(namespaceName,
//                ApolloConfigUtil.getParamFlowDataId(projectName), defaultRules, source -> JSON.parseObject(source,
//                new TypeReference<List<ParamFlowRule>>() {
//        }));
//        ParamFlowRuleManager.register2Property(paramFlowRuleDataSource.getProperty());
//    }
//
//    private void registerSystemRuleProperty() {
//        ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new ApolloDataSource<>(namespaceName,
//                ApolloConfigUtil.getSystemDataId(projectName), defaultRules, source -> JSON.parseObject(source,
//                new TypeReference<List<SystemRule>>() {
//        }));
//
//        SystemRuleManager.register2Property(systemRuleDataSource.getProperty());
//    }
//
//    private void registerFlowRuleProperty() {
//        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new ApolloDataSource<>(namespaceName,
//                ApolloConfigUtil.getFlowDataId(projectName), defaultRules, source -> JSON.parseObject(source,
//                new TypeReference<List<FlowRule>>() {
//        }));
//
//        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
//    }
//
//}
