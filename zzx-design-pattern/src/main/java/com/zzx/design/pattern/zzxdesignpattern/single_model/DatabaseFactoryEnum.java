package com.zzx.design.pattern.zzxdesignpattern.single_model;

/**
 * 枚举——懒加载单例模式
 * @author zhouzhixiang
 * @Date 2020-08-14
 */
public class DatabaseFactoryEnum {

    private DatabaseFactoryEnum() {

    }

    private enum EnumDatabase {
        INSTANCE;
        private DatabaseConnection instance = null;
        private EnumDatabase() {
            instance = new DatabaseConnection();
        }
        public DatabaseConnection getInstance() {
            return instance;
        }
    }

    public static DatabaseConnection getConnection() {
        return EnumDatabase.INSTANCE.getInstance();
    }
}
