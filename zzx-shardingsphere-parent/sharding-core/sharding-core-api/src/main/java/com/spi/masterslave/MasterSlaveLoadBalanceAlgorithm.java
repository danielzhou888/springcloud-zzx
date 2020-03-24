package com.spi.masterslave;

import com.spi.TypeBasedSPI;

import java.util.List;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
public interface MasterSlaveLoadBalanceAlgorithm extends TypeBasedSPI {

    String getDataSource(String name, String masterDataSourceName, List<String> slaveDataSourceNames);
}
