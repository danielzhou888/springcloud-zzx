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
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.NamespaceReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 降级规则持久化
 * @author hantianwei@gmail.com
 * @since 1.5.0
 */
@Component("degradeRuleApolloPublisher")
public class DegradeRuleApolloPublisher implements DynamicRulePublisher<List<DegradeRuleEntity>> {

    @Autowired
    private ApolloOpenApiClient apolloOpenApiClient;
    @Autowired
    private Converter<List<DegradeRuleEntity>, String> converter;

    @Autowired
    private ApolloProperty apolloProperty;

    @Override
    public void publish(String app, List<DegradeRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }

        String degradeDataId = ApolloConfigUtil.getDegradeDataId(app);
        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(degradeDataId);
        openItemDTO.setValue(converter.convert(rules));
        openItemDTO.setComment("Program auto-join");
        openItemDTO.setDataChangeCreatedBy("apollo");
        apolloOpenApiClient.createOrUpdateItem(apolloProperty.getAppId(), apolloProperty.getEnv(), apolloProperty.getClusterName(), apolloProperty.getNamespaceName(), openItemDTO);

        // Release configuration
        NamespaceReleaseDTO namespaceReleaseDTO = new NamespaceReleaseDTO();
        namespaceReleaseDTO.setEmergencyPublish(true);
        namespaceReleaseDTO.setReleaseComment("Modify or add configurations");
        namespaceReleaseDTO.setReleasedBy("apollo");
        //namespaceReleaseDTO.setReleasedBy("some-operator");
        namespaceReleaseDTO.setReleaseTitle("Modify or add configurations");
        apolloOpenApiClient.publishNamespace(apolloProperty.getAppId(), apolloProperty.getEnv(), apolloProperty.getClusterName(), apolloProperty.getNamespaceName(), namespaceReleaseDTO);
    }
}
