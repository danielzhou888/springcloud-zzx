package com.zzx.dubbo.spi.impl;

import com.zzx.dubbo.spi.Log;

/**
 * @author zhouzhixiang
 * @Date 2021-01-02
 */
public class Log4jLog implements Log {
    @Override
    public void print(String message) {
        System.out.println("Log4j " + message);
    }
}
