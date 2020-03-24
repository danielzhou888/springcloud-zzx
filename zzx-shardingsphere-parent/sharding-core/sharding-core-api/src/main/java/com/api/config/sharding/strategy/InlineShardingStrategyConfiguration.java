package com.api.config.sharding.strategy;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
@Getter
@ToString
public final class InlineShardingStrategyConfiguration implements ShardingStrategyConfiguration {

    private final String shardingColumn;

    private final String algorithmExpression;

    public InlineShardingStrategyConfiguration(final String shardingColumn, final String algorithmExpression) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(shardingColumn), "ShardingColumn is required");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(algorithmExpression), "AlgorithmExpression is required");
        this.shardingColumn = shardingColumn;
        this.algorithmExpression = algorithmExpression;
    }
}
