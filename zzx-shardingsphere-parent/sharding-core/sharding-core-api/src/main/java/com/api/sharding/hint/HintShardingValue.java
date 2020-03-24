package com.api.sharding.hint;

import com.api.sharding.ShardingValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Collection;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
@RequiredArgsConstructor
@Getter
@ToString
public final class HintShardingValue<T extends Comparable<?>> implements ShardingValue {

    private final String logicTableName;

    private final String columnName;

    private final Collection<T> values;
}
