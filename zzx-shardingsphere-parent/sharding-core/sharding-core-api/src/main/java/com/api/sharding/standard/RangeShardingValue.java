package com.api.sharding.standard;

import com.api.sharding.ShardingValue;
import com.google.common.collect.Range;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
@RequiredArgsConstructor
@Getter
@ToString
public final class RangeShardingValue<T extends Comparable<?>> implements ShardingValue {

    private final String logicTableName;

    private final String columnName;

    private final Range<T> valueRange;
}
