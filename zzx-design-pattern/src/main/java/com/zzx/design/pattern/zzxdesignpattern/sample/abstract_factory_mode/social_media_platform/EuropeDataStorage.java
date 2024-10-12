package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

/**
 * @Description: 欧洲数据存储类
 * @author: 周志祥
 * @create: 2024-10-12 17:49
 */
public class EuropeDataStorage implements DataStorage {
    @Override
    public void storeData(String data) {
        System.out.println("开始执行数据存储, 国家[欧洲], 云存储商[Google Cloud]");
    }
}
