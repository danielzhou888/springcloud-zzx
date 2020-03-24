package com.api.config.sharding.strategy;

import com.api.sharding.hint.HintShardingAlgorithm;
import com.google.common.base.Preconditions;
import lombok.Getter;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
@Getter
public final class HintShardingStrategyConfiguration implements ShardingStrategyConfiguration {

    private final HintShardingAlgorithm hintShardingAlgorithm;

    public HintShardingStrategyConfiguration(final HintShardingAlgorithm hintShardingAlgorithm) {
        Preconditions.checkNotNull(hintShardingAlgorithm, "HintShardingAlgorithm is required");
        this.hintShardingAlgorithm = hintShardingAlgorithm;
    }
}
