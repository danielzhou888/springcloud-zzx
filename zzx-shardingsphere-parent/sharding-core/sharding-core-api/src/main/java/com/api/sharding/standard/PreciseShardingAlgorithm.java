package com.api.sharding.standard;

import com.api.sharding.ShardingAlgorithm;

import java.util.Collection;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
public interface PreciseShardingAlgorithm<T extends Comparable<?>> extends ShardingAlgorithm {

    String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<T> shardingValue);
}
