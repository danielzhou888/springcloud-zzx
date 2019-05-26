package com.concurrency.chapter14;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-23 12:54
 */
public class DynamicDataSourceKey {
    private static final ThreadLocal<String> key = new ThreadLocal<>();

    public static String getKey() {
        return key.get();
    }

    public static void setDataSource(String dataSourceKey) {
        key.set(dataSourceKey);
    }

    public static void clearDataSource() {
        key.remove();
    }
}
