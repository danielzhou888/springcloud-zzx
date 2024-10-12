package com.zzx.design.pattern.zzxdesignpattern.sample.factory_mode.distribute_cloud_storage_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 图片存储类
 * @author: 周志祥
 * @create: 2024-10-12 16:14
 */
public class ImageStorage implements FileStorage {

    private static final Logger logger = LoggerFactory.getLogger(ImageStorage.class);

    private String cloudProvider;

    public ImageStorage(String cloudProvider) {
        this.cloudProvider = cloudProvider;
    }

    @Override
    public void storeFile(String fileName, byte[] fileData) {
        logger.info("存储图片文件前, 先进行压缩操作, 运营商：{}", cloudProvider);
        // 压缩图片逻辑
        byte[] compressData = compress(fileData);
        // 调用云存储服务上传文件
        uploadToCloud(fileName, compressData);
    }

    private void uploadToCloud(String fileName, byte[] compressData) {
        logger.info("上传图片到运营商[{}], fileName = {}", cloudProvider, fileName);
    }

    private byte[] compress(byte[] fileData) {
        logger.info("执行图片压缩");
        return fileData;
    }
}
