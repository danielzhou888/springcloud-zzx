package com.zzx.design.pattern.zzxdesignpattern.mashibin.simple_factory.chapter2;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
@Slf4j
@NoArgsConstructor
public class AppleMobile implements Mobile {

    @Override
    public void call() {
        log.info("apple mobile is calling ...");
    }
}
