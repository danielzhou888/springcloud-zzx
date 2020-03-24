package com.api.config.sharding;

import com.api.config.sharding.strategy.ShardingStrategyConfiguration;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
public class TableRuleConfiguration {

    private final String logicTable;

    private final String actualDataNodes;

    private ShardingStrategyConfiguration databaseShardingStrategyConfig;

    private ShardingStrategyConfiguration tableShardingStrategyConfig;

    private KeyGeneratorConfiguration keyGeneratorConfig;

    public TableRuleConfiguration(final String logicTable) {
        this(logicTable, null);
    }

    public TableRuleConfiguration(final String logicTable, final String actualDataNodes) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(logicTable), "LogicTable is required");
        this.logicTable = logicTable;
        this.actualDataNodes = actualDataNodes;
    }
}
