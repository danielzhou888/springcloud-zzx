package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

/**
 * @Description: 美国工厂
 * @author: 周志祥
 * @create: 2024-10-12 17:51
 */
public class USAFactory implements RegionFactory {
    @Override
    public UI createUI() {
        return new USAUI();
    }

    @Override
    public DataStorage createDataStorage() {
        return new USADataStorage();
    }
}
