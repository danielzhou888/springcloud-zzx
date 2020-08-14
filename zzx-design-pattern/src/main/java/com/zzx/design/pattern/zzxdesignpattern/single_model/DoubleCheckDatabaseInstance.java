package com.zzx.design.pattern.zzxdesignpattern.single_model;

import lombok.extern.slf4j.Slf4j;

/**
 * 双重检查锁数据库链接
 * @author zhouzhixiang
 * @Date 2020-08-14
 */
@Slf4j
public class DoubleCheckDatabaseInstance {

    private static volatile DatabaseConnection databaseConnection = null;

    private DoubleCheckDatabaseInstance() {}

    public static DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            synchronized (DoubleCheckDatabaseInstance.class) {
                if (databaseConnection == null) {
                    databaseConnection = new DatabaseConnection();
                    log.info("DatabaseConnection getInstance ... threadId = {}", Thread.currentThread().getId());
                }
            }
        }
        return databaseConnection;
    }
}
