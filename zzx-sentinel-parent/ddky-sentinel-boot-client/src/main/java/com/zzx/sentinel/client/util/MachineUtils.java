//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zzx.sentinel.client.util;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.zzx.sentinel.client.apollo.cluster.entity.ClusterGroupEntity;
import com.zzx.sentinel.client.log.RecordLog;

import java.net.SocketException;

public final class MachineUtils {



    private static String currentProcessConfigurationId = null;

    static {
        try {
            currentProcessConfigurationId = AddressUtils.getInnetIp();
        } catch (SocketException e) {
            RecordLog.error("MachineUtils static code run excetpion {}", e);
        }
    }

    public static String getCurrentProcessConfigurationId() {
        return currentProcessConfigurationId;
    }

    public static void setCurrentProcessConfigurationId(String currentProcessConfigurationId) {
        MachineUtils.currentProcessConfigurationId = currentProcessConfigurationId;
    }

    public static boolean isCurrentMachineEqual(ClusterGroupEntity group) {
        return StringUtil.isEmpty(currentProcessConfigurationId) ? false : currentProcessConfigurationId.equals(group.getIp());
        //return StringUtil.isEmpty(currentProcessConfigurationId) ? false : currentProcessConfigurationId.equals(group.getMachineId());
    }

    private MachineUtils() {
    }
}
