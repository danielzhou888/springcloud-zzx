package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 欧洲UI
 * @author: 周志祥
 * @create: 2024-10-12 17:43
 */
public class EuropeUI implements UI {

    private static Logger log = LoggerFactory.getLogger(EuropeUI.class);

    @Override
    public void display() {
        log.info("用多语言展示欧洲UI...");
    }
}
