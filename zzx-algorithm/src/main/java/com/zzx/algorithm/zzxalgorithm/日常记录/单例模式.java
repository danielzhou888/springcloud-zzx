package com.zzx.algorithm.zzxalgorithm.日常记录;

public class 单例模式 {

    private static volatile 单例模式 instance = null;

    private 单例模式 () {
    }

    public static 单例模式 getInstance() {
        if (instance == null) {
            synchronized (单例模式.class) {
                if (instance == null) {
                    instance = new 单例模式();
                }
            }
        }
        return instance;
    }
}
