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
package com.zzx.sentinel.client.config;

/**
 * @author zhouzhxiang
 */
public final class ApolloConfigUtil {

    public static final String FLOW_DATA_ID_POSTFIX = ".flow-rules";
    public static final String DEGRADE_DATA_ID_POSTFIX = ".degrade-rules";
    public static final String PARAM_FLOW_DATA_ID_POSTFIX = ".param-flow-rules";
    public static final String AUTHORITY_DATA_ID_POSTFIX = ".authority-rules";
    public static final String SYSTEM_DATA_ID_POSTFIX = ".system-rules";
    public static final String CLUSTER_MAP_DATA_ID_POSTFIX = ".cluster-map-data-rules";
    public static final String TOKEN_SERVER_CLUSTER_MAP_DATA_ID = "token-server-cluster-map";
    public static final String TOKEN_SERVER_NAMESPACE_NAME = "token-server";
    public static final String TOKEN_CLIENT_CONFIG_DATA_ID = "token-client-config-data-id";
    public static final String TOKEN_SERVER_NAMESPACE_DATA_ID = "token-server-namespace-set";

    private ApolloConfigUtil() {
    }

    public static String getFlowDataId(String appName) {
        return String.format("%s%s", appName, FLOW_DATA_ID_POSTFIX);
    }

    public static String getDegradeDataId(String appName) {
        return String.format("%s%s", appName, DEGRADE_DATA_ID_POSTFIX);
    }

    public static String getParamFlowDataId(String appName) {
        return String.format("%s%s", appName, PARAM_FLOW_DATA_ID_POSTFIX);
    }

    public static String getAuthorityDataId(String appName) {
        return String.format("%s%s", appName, AUTHORITY_DATA_ID_POSTFIX);
    }

    public static String getSystemDataId(String appName) {
        return String.format("%s%s", appName, SYSTEM_DATA_ID_POSTFIX);
    }

    public static String getClusterMapDataId(String appName) {
        return String.format("%s%s", appName, CLUSTER_MAP_DATA_ID_POSTFIX);
    }

    public static String getTokenServerClusterMapDataId(String appName) {
        return String.format("%s%s", appName, CLUSTER_MAP_DATA_ID_POSTFIX);
    }

    public static String getTokenServerNamespaceName() {
        return TOKEN_SERVER_NAMESPACE_NAME;
    }

    public static String getTokenClientConfigDataId() {
        return TOKEN_CLIENT_CONFIG_DATA_ID;
    }

    public static String getTokenServerNamespaceDataId() {
        return TOKEN_CLIENT_CONFIG_DATA_ID;
    }

}
