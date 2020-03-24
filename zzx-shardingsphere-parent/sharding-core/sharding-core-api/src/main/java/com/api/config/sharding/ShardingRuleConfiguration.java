package com.api.config.sharding;

import com.api.config.RuleConfiguration;
import com.api.config.encrypt.EncryptRuleConfiguration;
import com.api.config.masterslave.MasterSlaveRuleConfiguration;
import com.api.config.sharding.strategy.ShardingStrategyConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
@Getter
@Setter
public final class ShardingRuleConfiguration implements RuleConfiguration {

    private Collection<TableRuleConfiguration> tableRuleConfigs = new LinkedList<>();

    private Collection<String> bindingTableGroups = new LinkedList<>();

    private Collection<String> broadcastTables = new LinkedList<>();

    private String defaultDataSourceName;

    private ShardingStrategyConfiguration defaultDatabaseShardingStrategyConfig;

    private ShardingStrategyConfiguration defaultTableShardingStrategyConfig;

    private KeyGeneratorConfiguration defaultKeyGeneratorConfig;

    private Collection<MasterSlaveRuleConfiguration> masterSlaveRuleConfigs = new LinkedList<>();

    private EncryptRuleConfiguration encryptRuleConfig;
}
