package com.api.config.sharding.strategy;

import com.api.sharding.standard.PreciseShardingAlgorithm;
import com.api.sharding.standard.RangeShardingAlgorithm;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
public class StandardShardingStrategyConfiguration implements ShardingStrategyConfiguration {

    private final String shardingColummn;

    private final PreciseShardingAlgorithm preciseShardingAlgorithm;

    private final RangeShardingAlgorithm rangeShardingAlgorithm;

    public StandardShardingStrategyConfiguration(String shardingColummn, PreciseShardingAlgorithm preciseShardingAlgorithm) {
        this(shardingColummn, preciseShardingAlgorithm, null);
    }

    public StandardShardingStrategyConfiguration(final String shardingColummn, final PreciseShardingAlgorithm preciseShardingAlgorithm, final RangeShardingAlgorithm rangeShardingAlgorithm) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(shardingColummn), "ShardingColumn is required");
        Preconditions.checkNotNull(preciseShardingAlgorithm, "PreciseShardingAlgorithm is required");
        this.shardingColummn = shardingColummn;
        this.preciseShardingAlgorithm = preciseShardingAlgorithm;
        this.rangeShardingAlgorithm = rangeShardingAlgorithm;
    }
}
