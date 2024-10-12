package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

/**
 * @Description: 测试类
 * @author: 周志祥
 * @create: 2024-10-12 17:52
 */
public class Main {

    public static void main(String[] args) {
        // 针对美国用户，使用美国工厂
        RegionFactory usaFactory = new USAFactory();
        SocialMediaPlatform usaPlatform = new SocialMediaPlatform(usaFactory.createUI(), usaFactory.createDataStorage());
    }
}
