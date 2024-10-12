package com.zzx.design.pattern.zzxdesignpattern.sample.abstract_factory_mode.social_media_platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 中国UI
 * @author: 周志祥
 * @create: 2024-10-12 17:43
 */
public class ChinaUI implements UI {
    private static Logger log = LoggerFactory.getLogger(ChinaUI.class);

    @Override
    public void display() {
        log.info("用中文展示中国UI");
    }
}
