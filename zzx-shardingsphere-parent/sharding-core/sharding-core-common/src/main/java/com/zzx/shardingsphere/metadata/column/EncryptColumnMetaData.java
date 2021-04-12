package com.zzx.shardingsphere.metadata.column;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
@Getter
@EqualsAndHashCode
@ToString
public final class EncryptColumnMetaData extends ColumnMetaData {

    private final String cipherColumnName;

    private final String plainColumnName;

    private final String assistedQueryColumnName;

    public EncryptColumnMetaData(String name, String dataType, boolean primaryKey, boolean notNull, boolean autoIncrement, String cipherColumnName, String plainColumnName, String assistedQueryColumnName) {
        super(name, dataType, primaryKey, notNull, autoIncrement);
        this.cipherColumnName = cipherColumnName;
        this.plainColumnName = plainColumnName;
        this.assistedQueryColumnName = assistedQueryColumnName;
    }
}
