package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 美国UI
 * @author: 周志祥
 * @create: 2024-10-12 17:42
 */
public class USAUI implements UI {

    private static Logger log = LoggerFactory.getLogger(USAUI.class);

    @Override
    public void display() {
        log.info("用英文展示美国UI...");
    }
}
