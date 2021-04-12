package com.zzx.shardingsphere.metadata.column;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ShardingGeneratedKeyColumnMetaData extends ColumnMetaData {

    public ShardingGeneratedKeyColumnMetaData(String name, String dataType, boolean primaryKey, boolean notNull, boolean autoIncrement) {
        super(name, dataType, primaryKey, notNull, autoIncrement);
    }
}
