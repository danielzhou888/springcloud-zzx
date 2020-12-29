package com.zzx.sentinel.client.apollo.cluster.parser;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ctrip.framework.apollo.core.utils.StringUtils;
import com.zzx.sentinel.client.apollo.cluster.entity.ClusterGroupEntity;
import com.zzx.sentinel.client.log.RecordLog;
import com.zzx.sentinel.client.util.MachineUtils;

import java.util.Set;

/**
 * 集群中token server监听的
 * @author: zhouzhixiang
 */
public class ClusterAssignStateParser implements Converter<String, Integer> {

    private static final String EMPTY_JSON = "{}";

    @Override
    public Integer convert(String source) {
        if (source == null) {
            return null;
        }
        RecordLog.info("[ClusterClientAssignConfigParser] Get data: " + source);
        Set<String> nameSpaceList = JSON.parseObject(source, new TypeReference<Set<String>>() {
        });
        if (nameSpaceList == null || nameSpaceList.isEmpty()) {
            return ClusterStateManager.CLUSTER_NOT_STARTED;
        }
        return extractMode(nameSpaceList);
    }

    // Old methods
    //private int extractMode(Set<String> nameSpaceList) {
    //    if (nameSpaceList == null || nameSpaceList.isEmpty()) {
    //        return ClusterStateManager.CLUSTER_NOT_STARTED;
    //    }
    //
    //    String projectName = System.getProperty(AppNameUtil.getAppName());
    //
    //    if (nameSpaceList.contains(projectName)) {
    //        return ClusterStateManager.CLUSTER_CLIENT;
    //    }
    //    return ClusterStateManager.CLUSTER_NOT_STARTED;
    //}

    private int extractMode(Set<String> nameSpaceList) {
        if (nameSpaceList == null || nameSpaceList.isEmpty()) {
            return ClusterStateManager.CLUSTER_NOT_STARTED;
        }

        for (String config : nameSpaceList) {
            if (!StringUtils.isEmpty(config) && !EMPTY_JSON.equalsIgnoreCase(config)) {
                ClusterGroupEntity clusterGroupEntity = JSON.parseObject(config).toJavaObject(ClusterGroupEntity.class);
                if (clusterGroupEntity != null) {
                    if (MachineUtils.isCurrentMachineEqual(clusterGroupEntity)) {
                        return ClusterStateManager.CLUSTER_SERVER;
                    }
                    Set<String> clientSet = clusterGroupEntity.getClientSet();
                    if (clientSet != null && clientSet.size() > 0 && isClusterClient(clientSet)) {
                        return ClusterStateManager.CLUSTER_CLIENT;
                    }
                }
            } else {
                RecordLog.warn("extractMode warn sentinel-order-web.cluster-map-data-rules config is empty");
            }
        }
        return ClusterStateManager.CLUSTER_NOT_STARTED;
    }

    private boolean isClusterClient(Set<String> clientSet) {
        final String currentMachineIp = MachineUtils.getCurrentProcessConfigurationId();
        return clientSet.stream().filter(ip -> ip.contains(currentMachineIp)).count() > 0 ? true : false;
    }
}

