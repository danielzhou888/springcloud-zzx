package com.zzx.shardingsphere.strategy.masterslave;

import com.spi.masterslave.MasterSlaveLoadBalanceAlgorithm;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouzhixiang
 * @Date 2020-03-30
 */
@Getter
@Setter
public class RoundRobinMasterSlaveLoadBalanceAlgorithm implements MasterSlaveLoadBalanceAlgorithm {

    private Properties properties = new Properties();

    private static final ConcurrentHashMap<String, AtomicInteger> COUNTS = new ConcurrentHashMap<>();

    @Override
    public String getDataSource(String name, String masterDataSourceName, List<String> slaveDataSourceNames) {
        AtomicInteger count = COUNTS.containsKey(name) ? COUNTS.get(name) : new AtomicInteger(0);
        COUNTS.putIfAbsent(name, count);
        count.compareAndSet(slaveDataSourceNames.size(), 0);
        return slaveDataSourceNames.get(Math.abs(count.getAndIncrement()) % slaveDataSourceNames.size());
    }

    @Override
    public String getType() {
        return "ROUND_ROBIN";
    }
}
