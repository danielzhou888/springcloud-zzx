package com.zzx.shardingsphere.strategy.masterslave;

import com.spi.masterslave.MasterSlaveLoadBalanceAlgorithm;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zhouzhixiang
 * @Date 2020-03-30
 */
@Getter
@Setter
public final class RandomMasterSlaveLoadBalanceAlgorithm implements MasterSlaveLoadBalanceAlgorithm {

    private Properties properties = new Properties();

    @Override
    public String getDataSource(String name, String masterDataSourceName, List<String> slaveDataSourceNames) {
        return slaveDataSourceNames.get(ThreadLocalRandom.current().nextInt(slaveDataSourceNames.size()));
    }

    @Override
    public String getType() {
        return "RANDOM";
    }
}
