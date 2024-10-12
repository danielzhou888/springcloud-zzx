package com.zzx.design.pattern.zzxdesignpattern.sample.factory_mode.distribute_cloud_storage_system;

import org.apache.commons.lang.StringUtils;

/**
 * @Description: 存储工厂类
 * @author: 周志祥
 * @create: 2024-10-12 16:31
 */
public class StorageFactory {

    public static FileStorage getFileStorage(String cloudProvider, String fileType) {
        if (StringUtils.equalsIgnoreCase(fileType, "Image")) {
            return new ImageStorage(cloudProvider);
        } else if (StringUtils.equalsIgnoreCase(fileType, "Video")) {
            return new VideoStorage(cloudProvider);
        } else if (StringUtils.equalsIgnoreCase(fileType, "Document")) {
            return new DocumentStorage(cloudProvider);
        }
        throw new IllegalArgumentException("当前文件类型不支持");
    }
}
