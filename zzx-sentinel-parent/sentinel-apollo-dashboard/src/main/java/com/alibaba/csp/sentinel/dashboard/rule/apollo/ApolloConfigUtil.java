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

/**
 * @author zhouzhxiang
 */
public final class ApolloConfigUtil {

    public static final String FLOW_DATA_ID_POSTFIX = "flow-rules.";
    public static final String DEGRADE_DATA_ID_POSTFIX = "degrade-rules.";
    public static final String PARAM_FLOW_DATA_ID_POSTFIX = "param-flow-rules.";
    public static final String AUTHORITY_DATA_ID_POSTFIX = "authority-rules.";
    public static final String SYSTEM_DATA_ID_POSTFIX = "system-rules.";
    public static final String CLUSTER_MAP_DATA_ID_POSTFIX = "cluster-map-data-rules.";

    private ApolloConfigUtil() {
    }

    public static String getFlowDataId(String resourceName) {
        return String.format("%s%s", FLOW_DATA_ID_POSTFIX, replaceIllegalChar(resourceName));
    }

    public static String getDegradeDataId(String resourceName) {

        return String.format("%s%s", DEGRADE_DATA_ID_POSTFIX, replaceIllegalChar(resourceName));
    }

    public static String getParamFlowDataId(String resourceName) {
        return String.format("%s%s", PARAM_FLOW_DATA_ID_POSTFIX, replaceIllegalChar(resourceName));
    }

    public static String getAuthorityDataId(String resourceName) {
        return String.format("%s%s", AUTHORITY_DATA_ID_POSTFIX, replaceIllegalChar(resourceName));
    }

    public static String getSystemDataId(String resourceName) {
        return String.format("%s%s", SYSTEM_DATA_ID_POSTFIX, replaceIllegalChar(resourceName));
    }

    public static String getClusterMapDataId(String appName) {
        return String.format("%s%s", CLUSTER_MAP_DATA_ID_POSTFIX, appName);
    }

    public static String replaceIllegalChar(String resourceName) {
        if (resourceName.contains("/")) {
            resourceName = resourceName.replaceAll("/", "|");
        }
        return resourceName;
    }
}
