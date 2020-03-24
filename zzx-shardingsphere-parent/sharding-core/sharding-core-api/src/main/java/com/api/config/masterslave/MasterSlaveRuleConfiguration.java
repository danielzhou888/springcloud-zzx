package com.api.config.masterslave;

import com.api.config.RuleConfiguration;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.Getter;

import java.util.Collection;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
@Getter
public class MasterSlaveRuleConfiguration implements RuleConfiguration {

    private final String name;

    private final String masterDataSourceName;

    private final Collection<String> slaveDataSourceNames;

    private final LoadBalanceStrategyConfiguration loadBalanceStrategyConfiguration;

    public MasterSlaveRuleConfiguration(final String name, final String masterDataSourceName, final Collection<String> slaveDataSourceNames, final LoadBalanceStrategyConfiguration loadBalanceStrategyConfiguration) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "Name is required");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(masterDataSourceName), "MasterDataSourceName is required");
        Preconditions.checkArgument(slaveDataSourceNames != null && !slaveDataSourceNames.isEmpty(), "SlaveDataSourceNames is required");
        this.name = name;
        this.masterDataSourceName = masterDataSourceName;
        this.slaveDataSourceNames = slaveDataSourceNames;
        this.loadBalanceStrategyConfiguration = loadBalanceStrategyConfiguration;
    }
}
