package com.api.config.sharding.strategy;

import com.api.sharding.complex.ComplexKeysShardingAlgorithm;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.Getter;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
@Getter
public final class ComplexShardingStrategyConfiguration implements ShardingStrategyConfiguration {

    private final String shardingColumns;

    private final ComplexKeysShardingAlgorithm shardingAlgorithm;

    public ComplexShardingStrategyConfiguration(final String shardingColumns, final ComplexKeysShardingAlgorithm shardingAlgorithm) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(shardingColumns), "ShardingColumns is required");
        Preconditions.checkNotNull(shardingAlgorithm, "ShardingAlgorithm is required");
        this.shardingColumns = shardingColumns;
        this.shardingAlgorithm = shardingAlgorithm;
    }
}
