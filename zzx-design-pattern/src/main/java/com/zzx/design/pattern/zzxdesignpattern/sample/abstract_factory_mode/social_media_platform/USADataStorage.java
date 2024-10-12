package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

/**
 * @Description: USA数据存储类
 * @author: 周志祥
 * @create: 2024-10-12 17:44
 */
public class USADataStorage implements DataStorage {
    @Override
    public void storeData(String data) {
        System.out.println("开始执行数据存储, 国家[美国], 云存储供应商[AWS S3]");
    }
}
