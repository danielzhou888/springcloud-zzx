package com.spi;

import java.util.Properties;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
public interface TypeBasedSPI {

    String getType();

    Properties getProperties();

    void setProperties(Properties properties);
}
