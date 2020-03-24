package com.api.sharding.standard;

import com.api.sharding.ShardingAlgorithm;

import java.util.Collection;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
public interface RangeShardingAlgorithm<T extends Comparable<?>> extends ShardingAlgorithm {

    Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<T> shardingValue);
}
