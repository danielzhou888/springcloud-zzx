package com.api.sharding.hint;

import com.api.sharding.ShardingAlgorithm;

import java.util.Collection;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
public interface HintShardingAlgorithm<T extends Comparable<?>> extends ShardingAlgorithm {

    Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<T> shardingValue);
}
