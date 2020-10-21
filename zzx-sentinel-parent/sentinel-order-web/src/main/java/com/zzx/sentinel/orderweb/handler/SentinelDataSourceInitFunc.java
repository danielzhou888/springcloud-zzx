//package com.zzx.sentinel.orderweb.handler;
//
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
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//
//import java.util.List;
//
///**
// * Sentinel DataSource监听类 <br>
// * <pre>
// *     用于监听Sentinel Dashboard推送到apollo的规则信息，使客户端能动态实时更新规则信息
// * </pre>
// * @author zhouzhixiang
// * @Date 2020-10-21
// */
//public class SentinelDataSourceInitFunc implements InitFunc {
//
//    @Override
//    public void init() throws Exception {
//        final String appId = "90017";
//        final String apolloMetaServerAddress = "http://localhost:8082";
//        final String env = "FAT";
//        final String nameSpaceName = "application";
//        System.setProperty("app.id", appId);
//        System.setProperty("apollo.meta", apolloMetaServerAddress);
//        System.setProperty("env", env);
//
//        final String flowRuleKey = "sentinel-order-web-flow-rules";
//        final String defaultFlowRules = "[]";
//        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new ApolloDataSource<>(nameSpaceName, flowRuleKey, defaultFlowRules, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
//        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
//
//        final String degradeRuleKey = "sentinel-order-web-degrade-rules";
//        final String defaultDegradeRules = "[]";
//        ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new ApolloDataSource<>(nameSpaceName, degradeRuleKey, defaultDegradeRules, source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {}));
//        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
//
//        final String paramFlowRuleKey = "sentinel-order-web-param-flow-rules";
//        final String defaultParamFlowRules = "[]";
//        ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleDataSource = new ApolloDataSource<>(nameSpaceName, paramFlowRuleKey, defaultParamFlowRules, source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {}));
//        ParamFlowRuleManager.register2Property(paramFlowRuleDataSource.getProperty());
//
//        final String authorityRuleKey = "sentinel-order-web-authority-rules";
//        final String defaultAuthorityRules = "[]";
//        ReadableDataSource<String, List<AuthorityRule>> authorityRuleDataSource = new ApolloDataSource<>(nameSpaceName, authorityRuleKey, defaultAuthorityRules, source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {}));
//        AuthorityRuleManager.register2Property(authorityRuleDataSource.getProperty());
//
//        final String systemRuleKey = "sentinel-order-web-system-rules";
//        final String defaultSystemRules = "[]";
//        ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new ApolloDataSource<>(nameSpaceName, systemRuleKey, defaultSystemRules, source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {}));
//        SystemRuleManager.register2Property(systemRuleDataSource.getProperty());
//    }
//}
