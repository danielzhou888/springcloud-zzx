package com.zzx.sentinel.client.init;

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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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

    //@Autowired
    private static DdkySentinelProperties ddkySentinelProperties;

    private static final String FLOW_DATA_ID_POSTFIX = ".flow-rules";
    private static final String DEGRADE_DATA_ID_POSTFIX = ".degrade-rules";
    private static final String PARAM_FLOW_DATA_ID_POSTFIX = ".param-flow-rules";
    private static final String AUTHORITY_DATA_ID_POSTFIX = ".authority-rules";
    private static final String SYSTEM_DATA_ID_POSTFIX = ".system-rules";

    private static final String APP_ID = "app.id";
    private static final String APOLLO_META = "apollo.meta";
    private static final String ENV = "env";

    static {
        ddkySentinelProperties = DdkySentinelProperties.getInstances();
    }

    @Override
    public void init() throws Exception {
        final String appId = ddkySentinelProperties.getAppid();
        final String portalUrl = ddkySentinelProperties.getPortalUrl();
        final String env = ddkySentinelProperties.getEnv();
        final String nameSpaceName = ddkySentinelProperties.getNamespaceName();
        final String projectName = ddkySentinelProperties.getProjectName();
        System.setProperty(APP_ID, appId);
        System.setProperty(APOLLO_META, portalUrl);
        System.setProperty(ENV, env);

        RecordLog.info("SentinelDataSourceInitFunc init run : appid = {}, portalUrl = {}, env = {}, nameSpaceName = {}, projectName = {}", appId, portalUrl, env, nameSpaceName, projectName);

        final String flowRuleKey = String.format("%s%s", projectName, FLOW_DATA_ID_POSTFIX);
        final String defaultFlowRules = "[]";
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new ApolloDataSource<>(nameSpaceName, flowRuleKey, defaultFlowRules, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

        final String degradeRuleKey = String.format("%s%s", projectName, DEGRADE_DATA_ID_POSTFIX);
        final String defaultDegradeRules = "[]";
        ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new ApolloDataSource<>(nameSpaceName, degradeRuleKey, defaultDegradeRules, source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {}));
        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());

        final String paramFlowRuleKey = String.format("%s%s", projectName, PARAM_FLOW_DATA_ID_POSTFIX);
        final String defaultParamFlowRules = "[]";
        ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleDataSource = new ApolloDataSource<>(nameSpaceName, paramFlowRuleKey, defaultParamFlowRules, source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {}));
        ParamFlowRuleManager.register2Property(paramFlowRuleDataSource.getProperty());

        final String authorityRuleKey = String.format("%s%s", projectName, AUTHORITY_DATA_ID_POSTFIX);
        final String defaultAuthorityRules = "[]";
        ReadableDataSource<String, List<AuthorityRule>> authorityRuleDataSource = new ApolloDataSource<>(nameSpaceName, authorityRuleKey, defaultAuthorityRules, source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {}));
        AuthorityRuleManager.register2Property(authorityRuleDataSource.getProperty());

        final String systemRuleKey = String.format("%s%s", projectName, SYSTEM_DATA_ID_POSTFIX);
        final String defaultSystemRules = "[]";
        ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new ApolloDataSource<>(nameSpaceName, systemRuleKey, defaultSystemRules, source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {}));
        SystemRuleManager.register2Property(systemRuleDataSource.getProperty());
        RecordLog.info("SentinelDataSourceInitFunc init execute success");
    }
}
