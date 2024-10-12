package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

/**
 * @Description: 中国数据存储类
 * @author: 周志祥
 * @create: 2024-10-12 17:46
 */
public class ChinaDataStorage implements DataStorage {
    @Override
    public void storeData(String data) {
        System.out.println("开始执行数据存储, 国家[中国], 云存储商[Aliyun OSS]");
    }
}
