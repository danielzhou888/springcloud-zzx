package com.zzx.design.pattern.zzxdesignpattern.sample.factory_mode.distribute_cloud_storage_system;

/**
 * @Description: 存储基类接口
 * @author: 周志祥
 * @create: 2024-10-12 16:13
 */
public interface FileStorage {

    /**
     * 存储文件
     * @param fileName
     * @param fileData
     */
    void storeFile(String fileName, byte[] fileData);
}
