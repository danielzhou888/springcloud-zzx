package com.zzx.sentinel.client.init;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterParamFlowRuleManager;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerFlowConfig;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.apollo.ApolloDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.transport.config.TransportConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zzx.sentinel.client.cluster.parser.ClusterAssignConfigParser;
import com.zzx.sentinel.client.cluster.parser.ClusterAssignStateParser;
import com.zzx.sentinel.client.cluster.parser.ClusterServerFlowConfigParser;
import com.zzx.sentinel.client.cluster.parser.ClusterTransportConfigParser;
import com.zzx.sentinel.client.config.ApolloConfigUtil;
import com.zzx.sentinel.client.config.DdkySentinelProperties;
import com.zzx.sentinel.client.log.RecordLog;
import com.zzx.sentinel.client.util.AssertUtil;

import java.util.List;

/**
 * Sentinel DataSource监听类 <br>
 * <pre>
 *     用于监听Sentinel Dashboard推送到apollo的规则信息，使客户端能动态实时更新规则信息
 * </pre>
 * @author zhouzhixiang
 * @Date 2020-10-21
 */
public class SentinelDataSourceInitFunc implements InitFunc {

    private static DdkySentinelProperties ddkySentinelProperties;

    private static final String FLOW_DATA_ID_POSTFIX = ".flow-rules";
    private static final String DEGRADE_DATA_ID_POSTFIX = ".degrade-rules";
    private static final String PARAM_FLOW_DATA_ID_POSTFIX = ".param-flow-rules";
    private static final String AUTHORITY_DATA_ID_POSTFIX = ".authority-rules";
    private static final String SYSTEM_DATA_ID_POSTFIX = ".system-rules";

    private static final String APP_ID = "app.id";
    private static final String APOLLO_META = "apollo.meta";
    private static final String ENV = "env";

    private String projectName;
    private String nameSpaceName;
    private String tokenServerNamespaceName;

    //private final String defaultFlowRules = "[]";
    //private final String defaultDegradeRules = "[]";
    //private final String defaultSystemRules = "[]";
    //private final String defaultAuthorityRules = "[]";
    //private final String defaultParamFlowRules = "[]";
    private final String defaultRules = "[]";

    static {
        ddkySentinelProperties = DdkySentinelProperties.getInstances();
    }

    @Override
    public void init() throws Exception {
        final String appId = ddkySentinelProperties.getAppid();
        final String portalUrl = ddkySentinelProperties.getPortalUrl();
        final String env = ddkySentinelProperties.getEnv();
        //final String nameSpaceName = ddkySentinelProperties.getNamespaceName();
        //final String projectName = ddkySentinelProperties.getProjectName();

        projectName = ddkySentinelProperties.getProjectName();
        nameSpaceName = ddkySentinelProperties.getNamespaceName();
        tokenServerNamespaceName = ddkySentinelProperties.getTokenServerNamespaceName();

        System.setProperty(APP_ID, appId);
        System.setProperty(APOLLO_META, portalUrl);
        System.setProperty(ENV, env);

        RecordLog.info("SentinelDataSourceInitFunc init run : appid = {}, portalUrl = {}, env = {}, nameSpaceName = {}, tokenServerNamespaceName = {}, projectName = {}", appId, portalUrl, env, nameSpaceName, tokenServerNamespaceName, projectName);


        initFlowRuleConfig();
        initDegradeRuleConfig();
        initParamFlowRuleConfig();
        initAuthorityRuleConfig();
        initSystemRuleConfig();
        initClusterConfig();



        RecordLog.info("SentinelDataSourceInitFunc init execute success");


    }

    //private void initClusterConfig() {

    //}

    private void initSystemRuleConfig() {
        final String systemRuleKey = String.format("%s%s", projectName, SYSTEM_DATA_ID_POSTFIX);
        ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new ApolloDataSource<>(nameSpaceName, systemRuleKey, defaultRules, source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {}));
        SystemRuleManager.register2Property(systemRuleDataSource.getProperty());
    }

    private void initAuthorityRuleConfig() {
        final String authorityRuleKey = String.format("%s%s", projectName, AUTHORITY_DATA_ID_POSTFIX);
        ReadableDataSource<String, List<AuthorityRule>> authorityRuleDataSource = new ApolloDataSource<>(nameSpaceName, authorityRuleKey, defaultRules, source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {}));
        AuthorityRuleManager.register2Property(authorityRuleDataSource.getProperty());

    }

    private void initFlowRuleConfig() {
        final String flowRuleKey = String.format("%s%s", projectName, FLOW_DATA_ID_POSTFIX);
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new ApolloDataSource<>(nameSpaceName, flowRuleKey, defaultRules, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    private void initDegradeRuleConfig() {
        final String degradeRuleKey = String.format("%s%s", projectName, DEGRADE_DATA_ID_POSTFIX);
        ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new ApolloDataSource<>(nameSpaceName, degradeRuleKey, defaultRules, source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {}));
        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
    }

    private void initParamFlowRuleConfig() {
        final String paramFlowRuleKey = String.format("%s%s", projectName, PARAM_FLOW_DATA_ID_POSTFIX);
        ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleDataSource = new ApolloDataSource<>(nameSpaceName, paramFlowRuleKey, defaultRules, source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {}));
        ParamFlowRuleManager.register2Property(paramFlowRuleDataSource.getProperty());
    }


    private void initClusterConfig() {
        //初始化token server端口相关配置
        initServerTransportConfigProperty();
        //最大token qps配置
        initServerFlowConfig();
        //为token server添加命名空间动态监听器
        initClusterRuleSupplier();
        //为每个client设置目标token server
        initClientServerAssignProperty();
        //初始化token client通用超时配置
        initClientConfigProperty();
        //等待transport端口分配完毕
        while (TransportConfig.getRuntimePort() == -1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //初始化客户端状态为client 或者 server
        initStateProperty();
    }

    private void initServerFlowConfig() {
        ClusterServerFlowConfigParser serverFlowConfigParser = new ClusterServerFlowConfigParser();
        ReadableDataSource<String, ServerFlowConfig> serverFlowConfigDs = new ApolloDataSource<>(tokenServerNamespaceName,
                ApolloConfigUtil.getTokenServerClusterMapDataId(projectName), defaultRules, s -> {
            ServerFlowConfig config = serverFlowConfigParser.convert(s);
            if (config != null) {
                ClusterServerConfigManager.loadGlobalFlowConfig(config);
            }
            return config;
        });
    }

    private void initStateProperty() {
        // Cluster map format:
        // [{"clientSet":["112.12.88.66@8729","112.12.88.67@8727"],"ip":"112.12.88.68","machineId":"112.12.88.68@8728","port":11111}]
        // machineId: <ip@commandPort>, commandPort for port exposed to Sentinel dashboard (transport module)
        ReadableDataSource<String, Integer> clusterModeDs = new ApolloDataSource<>(tokenServerNamespaceName,
                ApolloConfigUtil.getTokenServerClusterMapDataId(projectName), defaultRules, new ClusterAssignStateParser());
        ClusterStateManager.registerProperty(clusterModeDs.getProperty());
    }

    private void initServerTransportConfigProperty() {

        ReadableDataSource<String, ServerTransportConfig> serverTransportDs = new ApolloDataSource<>(tokenServerNamespaceName,
                ApolloConfigUtil.getTokenServerClusterMapDataId(projectName), defaultRules, new ClusterTransportConfigParser());
        ClusterServerConfigManager.registerServerTransportProperty(serverTransportDs.getProperty());
    }

    private void initClientServerAssignProperty() {
        // Cluster map format:
        // [{"clientSet":["112.12.88.66@8729","112.12.88.67@8727"],"ip":"112.12.88.68","machineId":"112.12.88.68@8728","port":11111}]
        // machineId: <ip@commandPort>, commandPort for port exposed to Sentinel dashboard (transport module)
        ReadableDataSource<String, ClusterClientAssignConfig> clientAssignDs = new ApolloDataSource<>(tokenServerNamespaceName,
                ApolloConfigUtil.getTokenServerClusterMapDataId(projectName), defaultRules, new ClusterAssignConfigParser());
        ClusterClientConfigManager.registerServerAssignProperty(clientAssignDs.getProperty());
    }

    private void initClientConfigProperty() {
        ReadableDataSource<String, ClusterClientConfig> clientConfigDs = new ApolloDataSource<>(tokenServerNamespaceName,
                ApolloConfigUtil.getTokenClientConfigDataId(), defaultRules, source -> JSON.parseObject(source, new TypeReference<ClusterClientConfig>() {
        }));
        ClusterClientConfigManager.registerClientConfigProperty(clientConfigDs.getProperty());
    }

    /**
     * 初始化集群限流规则监听器
     */
    private void initClusterRuleSupplier() {
        // Register cluster flow rule property supplier which creates data source by namespace.
        ClusterFlowRuleManager.setPropertySupplier(namespace -> {
            ReadableDataSource<String, List<FlowRule>> ds = new ApolloDataSource<>(nameSpaceName,
                    ApolloConfigUtil.getFlowDataId(projectName), defaultRules, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                    //ApolloConfigUtil.getFlowDataId(projectName), defaultRules, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
            }));
            return ds.getProperty();
        });

        // Register cluster parameter flow rule property supplier.
        ClusterParamFlowRuleManager.setPropertySupplier(namespace -> {
            ReadableDataSource<String, List<ParamFlowRule>> ds = new ApolloDataSource<>(nameSpaceName,
                    ApolloConfigUtil.getParamFlowDataId(projectName), defaultRules, source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {
            }));

            return ds.getProperty();
        });
    }


}
