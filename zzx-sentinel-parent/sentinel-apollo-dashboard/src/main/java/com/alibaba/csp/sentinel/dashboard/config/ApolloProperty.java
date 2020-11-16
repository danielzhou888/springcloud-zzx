package com.alibaba.csp.sentinel.dashboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Apollo配置类
 * @author zhouzhixiang
 * @Date 2020-10-20
 */
@Component
public class ApolloProperty {

    @Value("${apollo.appid}")
    private String appId;

    @Value("${apollo.env}")
    private String env;

    @Value("${apollo.cluster.name}")
    private String clusterName;

    @Value("${apollo.namespaceName}")
    private String namespaceName;

    @Value("${apollo.portal.url}")
    private String portalUrl;

    @Value("${apollo.third.token}")
    private String token;

    @Value("${apollo.token.server.namespaceName}")
    private String tokenServerNamespaceName;


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

    public String getPortalUrl() {
        return portalUrl;
    }

    public void setPortalUrl(String portalUrl) {
        this.portalUrl = portalUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenServerNamespaceName() {
        return this.tokenServerNamespaceName;
    }

    public void setTokenServerNamespaceName(final String tokenServerNamespaceName) {
        this.tokenServerNamespaceName = tokenServerNamespaceName;
    }
}
