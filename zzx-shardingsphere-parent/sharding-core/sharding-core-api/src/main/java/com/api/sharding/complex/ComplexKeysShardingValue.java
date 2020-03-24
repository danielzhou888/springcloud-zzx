package com.api.sharding.complex;

import com.api.sharding.ShardingValue;
import com.google.common.collect.Range;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
@RequiredArgsConstructor
@Getter
@ToString
public final class ComplexKeysShardingValue<T extends Comparable<?>> implements ShardingValue {

    private final String logicTableName;

    private final Map<String, Collection<T>> columnNameAndShardingValuesMap;

    private final Map<String, Range<T>> columnNameAndRangeValuesMap;
}
