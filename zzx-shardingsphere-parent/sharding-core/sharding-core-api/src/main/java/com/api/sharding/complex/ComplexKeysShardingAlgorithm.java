package com.api.sharding.complex;

import com.api.sharding.ShardingAlgorithm;

import java.util.Collection;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
public interface ComplexKeysShardingAlgorithm<T extends Comparable<?>> extends ShardingAlgorithm {

    Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<T> shardingValue);
}
