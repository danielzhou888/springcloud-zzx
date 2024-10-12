package com.zzx.design.pattern.zzxdesignpattern.sample.factory_mode.distribute_cloud_storage_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 视频文件存储类
 * @author: 周志祥
 * @create: 2024-10-12 16:20
 */
public class VideoStorage implements FileStorage {

    private static final Logger logger = LoggerFactory.getLogger(ImageStorage.class);

    private String cloudProvider;

    public VideoStorage(String cloudProvider) {
        this.cloudProvider = cloudProvider;
    }

    @Override
    public void storeFile(String fileName, byte[] fileData) {
        logger.info("上传视频文件前, 需要先进行加密后进行分段存储");
        // 视频加密和分段逻辑
        byte[] encryptedData = encrypt(fileData);
        byte[][] videoChunks = splitIntoChunks(encryptedData);

        // 调用云存储服务上传分段文件
        for (int i = 0; i < videoChunks.length; i++) {
            uploadToCloud(fileName + "_part" + i, videoChunks[i]);
        }
    }

    private void uploadToCloud(String filePartName, byte[] videoChunk) {
        logger.info("执行上传视频文件, filePartName = {}, 云储存供应商 = {}", filePartName, cloudProvider);
    }

    private byte[][] splitIntoChunks(byte[] encryptedData) {
        logger.info("执行视频分段处理...");
        return new byte[][] {encryptedData};
    }

    private byte[] encrypt(byte[] fileData) {
        logger.info("执行视频文件加密");
        return fileData;
    }
}
