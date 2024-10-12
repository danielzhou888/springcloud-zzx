package com.zzx.design.pattern.zzxdesignpattern.sample.factory_mode.distribute_cloud_storage_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 文档存储类
 * @author: 周志祥
 * @create: 2024-10-12 16:27
 */
public class DocumentStorage implements FileStorage {

    private static final Logger logger = LoggerFactory.getLogger(DocumentStorage.class);

    private String cloudProvider;

    public DocumentStorage(String cloudProvider) {
        this.cloudProvider = cloudProvider;
    }

    @Override
    public void storeFile(String fileName, byte[] fileData) {
        logger.info("上传文档前, 需要对文档进行加密, 云存储供应商 = {}", cloudProvider);
        // 文档加密逻辑
        byte[] encryptedData = encrypt(fileData);
        // 调用云存储服务上传文件
        uploadToCloud(fileName, encryptedData);
    }

    private void uploadToCloud(String fileName, byte[] encryptedData) {
        logger.info("上传文档到云存储供应商[], fileName = {}", cloudProvider, fileName);
    }

    private byte[] encrypt(byte[] fileData) {
        logger.info("对文档执行加密操作");
        return fileData;
    }
}
