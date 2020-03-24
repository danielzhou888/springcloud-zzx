package com.spi.database;

import java.util.Collection;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
public interface DatabaseType {

    String getName();

    Collection<String> getJdbcUrlPrefixAlias();

    DataSourceMetaData getDataSourceMetaData(String url);
}
