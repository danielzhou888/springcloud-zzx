package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

/**
 * 抽象工厂模式案例-社交媒体平台
 * https://app.yinxiang.com/fx/ae6cf061-4698-468e-808e-c9555cd0d8d1
 * @Description: 测试类
 * @author: 周志祥
 * @create: 2024-10-12 17:52
 */
public class Main {

    public static void main(String[] args) {
        // 针对美国用户，使用美国工厂
        RegionFactory usaFactory = new USAFactory();
        SocialMediaPlatform usaPlatform = new SocialMediaPlatform(usaFactory.createUI(), usaFactory.createDataStorage());
        usaPlatform.displayUI();
        usaPlatform.storeData("Data For USA");

        // 针对中国用户，使用中国工厂
        RegionFactory chinaFactory = new ChinaFactory();
        SocialMediaPlatform chinaPlatform = new SocialMediaPlatform(chinaFactory.createUI(), chinaFactory.createDataStorage());
        chinaPlatform.displayUI();
        chinaPlatform.storeData("Data For China");

        // 针对欧洲用户，使用欧洲工厂
        RegionFactory europeFactory = new EuropeFactory();
        SocialMediaPlatform europePlatform = new SocialMediaPlatform(europeFactory.createUI(), europeFactory.createDataStorage());
        europePlatform.displayUI();
    }
}
