package com.zzx.sentinel.client.apollo.cluster.parser;

import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zzx.sentinel.client.apollo.cluster.entity.ClusterGroupEntity;
import com.zzx.sentinel.client.log.RecordLog;
import com.zzx.sentinel.client.util.MachineUtils;

import java.util.List;

public class ClusterTransportConfigParser implements Converter<String, ServerTransportConfig> {
    @Override
    public ServerTransportConfig convert(String source) {
        if (source == null) {
            return null;
        }
        RecordLog.info("[ClusterServerTransportConfigParser] Get data: " + source);
        List<ClusterGroupEntity> groupList = JSON.parseObject(source, new TypeReference<List<ClusterGroupEntity>>() {});

        if (groupList == null || groupList.isEmpty()) {
            return null;
        }
        return extractServerTransportConfig(groupList);
    }

    private ServerTransportConfig extractServerTransportConfig(List<ClusterGroupEntity> groupList) {
        for (ClusterGroupEntity group : groupList) {
            if (MachineUtils.isCurrentMachineEqual(group)) {
                return new ServerTransportConfig().setPort(group.getPort()).setIdleSeconds(600);
            }
        }
        return null;
    }
}

