package com.zzx.shardingsphere.metadata.column;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ColumnMetaData {

    private final String name;

    private final String dataType;

    private final boolean primaryKey;

    private final boolean notNull;

    private final boolean autoIncrement;
}
