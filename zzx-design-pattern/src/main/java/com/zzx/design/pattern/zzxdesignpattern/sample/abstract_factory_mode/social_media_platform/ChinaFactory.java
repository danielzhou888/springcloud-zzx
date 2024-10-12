package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

/**
 * @Description: 中国工厂
 * @author: 周志祥
 * @create: 2024-10-12 17:51
 */
public class ChinaFactory implements RegionFactory {
    @Override
    public UI createUI() {
        return new ChinaUI();
    }

    @Override
    public DataStorage createDataStorage() {
        return new ChinaDataStorage();
    }
}
