package com.api.sharding.standard;

import com.api.sharding.ShardingValue;
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
public final class PreciseShardingValue<T extends Comparable<?>> implements ShardingValue {

    private final String logicTableName;

    private final String columnName;

    private final T value;
}
