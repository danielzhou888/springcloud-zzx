package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

/**
 * @Description: 欧洲工厂
 * @author: 周志祥
 * @create: 2024-10-12 17:52
 */
public class EuropeFactory implements RegionFactory {
    @Override
    public UI createUI() {
        return new EuropeUI();
    }

    @Override
    public DataStorage createDataStorage() {
        return new EuropeDataStorage();
    }
}
