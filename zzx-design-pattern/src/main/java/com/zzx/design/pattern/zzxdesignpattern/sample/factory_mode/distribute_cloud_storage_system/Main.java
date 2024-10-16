package com.zzx.design.pattern.zzxdesignpattern.sample.factory_mode.distribute_cloud_storage_system;

/**
 * 工厂模式案例-分布式的云存储系统
 * https://app.yinxiang.com/fx/96456f82-fb8b-4045-84ba-03d8be9ab06e
 * @Description: 测试类
 * @author: 周志祥
 * @create: 2024-10-12 16:35
 */
public class Main {

    public static void main(String[] args) {
        byte[] sampleData = new byte[1024]; // 模拟数据

        FileStorage imageStorage = StorageFactory.getFileStorage("AWS S3", "Image");
        imageStorage.storeFile("1.jpg", sampleData);

        FileStorage videoStorage = StorageFactory.getFileStorage("Google Cloud Storage", "Video");
        videoStorage.storeFile("2.mp4", sampleData);

        FileStorage documentStorage = StorageFactory.getFileStorage("Aliyun OSS", "Document");
        documentStorage.storeFile("3.pdf", sampleData);
    }
}
