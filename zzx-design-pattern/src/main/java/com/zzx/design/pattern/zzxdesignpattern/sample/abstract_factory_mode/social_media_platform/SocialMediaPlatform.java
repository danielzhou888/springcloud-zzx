package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

/**
 * @Description:
 * @author: 周志祥
 * @create: 2024-10-12 17:54
 */
public class SocialMediaPlatform {

    private UI ui;
    private DataStorage dataStorage;

    public SocialMediaPlatform(UI ui, DataStorage dataStorage) {
        this.ui = ui;
        this.dataStorage = dataStorage;
    }

    public void displayUI() {
        ui.display();
    }

    public void storeData(String data) {
        dataStorage.storeData(data);
    }
}
