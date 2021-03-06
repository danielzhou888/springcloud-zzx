package com.zzx.sentinel.client.config;

import java.io.*;
import java.util.Properties;
import com.zzx.sentinel.client.log.RecordLog;
import com.zzx.sentinel.client.util.AssertUtil;

/**
 * 叮当sentinel配置文件（对于SpringMvc项目：sentinel-apollo.properties，对于Springboot项目：application.properties）
 * @author zhouzhixiang
 * @Date 2020-10-21
 */

public class DdkySentinelProperties {
    public static final String APPID = "ddky.sentinel.apollo.appid";
    public static final String ENV = "ddky.sentinel.apollo.env";
    public static final String CLUSTER_NAME = "ddky.sentinel.apollo.cluster.name";
    public static final String TOKEN_SERVER_NAMESPACE_NAME = "ddky.sentinel.apollo.token.server.namespace.name";
    public static final String PORTAL_URL = "ddky.sentinel.apollo.portal.url";
    public static final String PROJECT_NAME = "project.name";

    private final String FILE_NAME = "application.properties";
    private final String FILE_NAME_SENTINEL = "sentinel.properties";
    private static volatile DdkySentinelProperties instances ;

    private final Properties properties  = new Properties();
    private boolean threadDetected=true;
    private String appid;
    private String env;
    private String clusterName = "default";
    private String tokenServerNamespaceName;
    private String portalUrl;
    private String projectName;

    public static DdkySentinelProperties getInstances () {
        if (instances == null) {
            synchronized  (DdkySentinelProperties.class) {
                if (instances == null) {
                    instances = new DdkySentinelProperties();
                }
            }
        }
        return instances;
    }
    
    private DdkySentinelProperties () {   
        init();
    }
    
    private void init() {
        // 优先读取sentinel.properties文件
        File f = new File(FILE_NAME_SENTINEL);
        InputStream inputStream = null;
        String fileName = FILE_NAME_SENTINEL;
        if (!f.exists()) {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            inputStream = loader.getResourceAsStream(fileName);
            if (inputStream == null) {
                fileName = FILE_NAME;
                inputStream = loader.getResourceAsStream(fileName);
                if (inputStream == null) {
                    throw new RuntimeException("缺少Sentinel配置文件");
                }
            }
        } else {
            try {
                inputStream = new FileInputStream(f);
            } catch (FileNotFoundException e) {
                RecordLog.error("DdkySentinelProperties init error {}", e);
            }
        }
        if (inputStream != null) {
            RecordLog.info ("init DdkySentinelProperties for " +fileName);
            try {
                byte [] b = new byte [inputStream.available()];
                inputStream.read(b);
                String str = new String(b);
                properties.load(new StringReader(str));
            } catch (FileNotFoundException e) {
                RecordLog.error("DdkySentinelProperties init",e);
            } catch (IOException e) {
                RecordLog.error("DdkySentinelProperties init",e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    RecordLog.error("DdkySentinelProperties init error {}", e);
                }
            }

            AssertUtil.notEmpty(properties.getProperty(APPID), "sentinel apollo config field ddky.sentinel.apollo.appid cannot be empty");
            appid = properties.getProperty(APPID);

            AssertUtil.notEmpty(properties.getProperty(ENV), "sentinel apollo config field ddky.sentinel.apollo.env cannot be empty");
            env = properties.getProperty(ENV);

            AssertUtil.notEmpty(properties.getProperty(CLUSTER_NAME), "sentinel apollo config field ddky.sentinel.apollo.cluster.name cannot be empty");
            clusterName = properties.getProperty(CLUSTER_NAME);

            AssertUtil.notEmpty(properties.getProperty(TOKEN_SERVER_NAMESPACE_NAME), "sentinel apollo config field ddky.sentinel.apollo.token.server.namespace.name cannot be empty");
            tokenServerNamespaceName = properties.getProperty(TOKEN_SERVER_NAMESPACE_NAME);

            AssertUtil.notEmpty(properties.getProperty(PORTAL_URL), "sentinel apollo config field ddky.sentinel.apollo.portal.url cannot be empty");
            portalUrl = properties.getProperty(PORTAL_URL);

            AssertUtil.notEmpty(properties.getProperty(PROJECT_NAME), "sentinel apollo config field ddky.sentinel.apollo.project.name cannot be empty");
            projectName = properties.getProperty(PROJECT_NAME);
            
        } else {
            RecordLog.warn("can not found " + fileName  +" for init DdkySentinelProperties");
        }
    }


    
    public boolean isThreadDetected() {
        return threadDetected;
    }

    public void setThreadDetected(boolean threadDetected) {
        this.threadDetected = threadDetected;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getAppid() {
        return this.appid;
    }

    public String getEnv() {
        return this.env;
    }

    public String getClusterName() {
        return this.clusterName;
    }

    public String getPortalUrl() {
        return this.portalUrl;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getTokenServerNamespaceName() {
        return this.tokenServerNamespaceName;
    }
}
