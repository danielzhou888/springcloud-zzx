package com.zzx.design.pattern.zzxdesignpattern.single_model;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式——数据库链接
 * @author zhouzhixiang
 * @Date 2020-08-14
 */
@Slf4j
public class DatabaseInstance {

    private DatabaseInstance() {

    }

    private enum DatabaseInstanceEnum {
        INSTANCE;
        private DatabaseConnection databaseConnection;

        private DatabaseInstanceEnum() {
            log.info("DatabaseInstance new instance ... threadId = {}", Thread.currentThread().getId());
            databaseConnection = new DatabaseConnection();
        }

        public DatabaseConnection getInstance() {
            return databaseConnection;
        }

    }

    public static DatabaseConnection getInstance() {
        return DatabaseInstanceEnum.INSTANCE.getInstance();
    }

}
