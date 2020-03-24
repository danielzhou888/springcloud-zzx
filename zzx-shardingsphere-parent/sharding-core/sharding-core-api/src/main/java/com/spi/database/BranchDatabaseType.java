package com.spi.database;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
public interface BranchDatabaseType extends DatabaseType {

    DatabaseType getTrunkDatabaseType();
}
