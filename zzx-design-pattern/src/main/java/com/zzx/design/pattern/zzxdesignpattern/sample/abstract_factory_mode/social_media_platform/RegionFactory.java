package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

/**
 * @Description: 区域工厂
 * @author: 周志祥
 * @create: 2024-10-12 17:49
 */
public interface RegionFactory {

    UI createUI();

    DataStorage createDataStorage();
}
