package com.zzx.design.pattern.zzxdesignpattern.single_model;

/**
 * 单例模式
 * @author zhouzhixiang
 * @Date 2020-08-14
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                DoubleCheckDatabaseInstance.getInstance();
                //DatabaseInstance.getInstance();
            }).start();
        }

        Thread.sleep(1000000);
    }
}
