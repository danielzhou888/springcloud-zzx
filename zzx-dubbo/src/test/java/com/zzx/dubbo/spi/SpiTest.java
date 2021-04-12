package com.zzx.dubbo.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author zhouzhixiang
 * @Date 2021-01-02
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<Log> load = ServiceLoader.load(Log.class);
        Iterator<Log> iterator = load.iterator();
        while (iterator.hasNext()) {
            Log log = iterator.next();
            log.print("JDK SPI");
        }
    }
}
