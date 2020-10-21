package com.zzx.sentinel.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Apollo配置类
 * @author zhouzhixiang
 * @Date 2020-10-20
 */
@Component
@ConfigurationProperties(prefix = "sentinel.apollo")
public class ApolloProperty {

    private String appId;

    private String env;

    private String clusterName;

    private String namespaceName;

    private String sentinelMetaServer;

    private String projectName;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getNamespaceName() {
        return namespaceName;
    }

    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    public String getSentinelMetaServer() {
        return this.sentinelMetaServer;
    }

    public void setSentinelMetaServer(final String sentinelMetaServer) {
        this.sentinelMetaServer = sentinelMetaServer;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }
}
