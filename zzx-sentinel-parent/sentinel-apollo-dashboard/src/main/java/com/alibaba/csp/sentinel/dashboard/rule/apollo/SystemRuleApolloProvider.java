/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.config.ApolloProperty;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.util.AppNameUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenNamespaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统规则apollo持久化
 * @author hantianwei@gmail.com
 * @since 1.5.0
 */
@Component("systemRuleApolloProvider")
public class SystemRuleApolloProvider implements DynamicRuleProvider<List<SystemRuleEntity>> {

    @Autowired
    private ApolloOpenApiClient apolloOpenApiClient;
    @Autowired
    private Converter<String, List<SystemRuleEntity>> converter;

    @Autowired
    private ApolloProperty apolloProperty;

    @Override
    public List<SystemRuleEntity> getRules(String appName) throws Exception {
        //String flowDataId = ApolloConfigUtil.getSystemDataId(appName);
        String flowDataIdSuffix = ApolloConfigUtil.SYSTEM_DATA_ID_POSTFIX;
        OpenNamespaceDTO openNamespaceDTO = apolloOpenApiClient.getNamespace(apolloProperty.getAppId(), apolloProperty.getEnv(), apolloProperty.getClusterName(), AppNameUtil.getCurrentAppNameSpace(appName));
        List<String> rules = openNamespaceDTO
                .getItems()
                .stream()
                .filter(p -> p.getKey().startsWith(flowDataIdSuffix))
                .map(OpenItemDTO::getValue)
                .collect(Collectors.toList());
        if (rules == null || rules.size() <= 0) {
            return new ArrayList<>();
        }

        List<SystemRuleEntity> ruleList = new ArrayList<>();
        for (String rule : rules) {
            ruleList.addAll(converter.convert(rule));
        }

        return ruleList;
    }
}
