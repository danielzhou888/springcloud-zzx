package com.zzx.shardingsphere.metadata.table;

import com.zzx.shardingsphere.metadata.column.ColumnMetaData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
@Getter
@EqualsAndHashCode
@ToString
public final class TableMetaData {

    private final Map<String, ColumnMetaData> columns;

    private final Collection<String> indexes;

    public TableMetaData(final Collection<ColumnMetaData> columnMetaDataList, final Collection<String> indexes) {
        this.columns = getColumns(columnMetaDataList);
        this.indexes = indexes;
    }

    public Map<String, ColumnMetaData> getColumns(final Collection<ColumnMetaData> columnMetaDataList) {
        Map<String, ColumnMetaData> columns = new LinkedHashMap<>();
        for (ColumnMetaData each : columnMetaDataList) {
            columns.put(each.getName().toLowerCase(), each);
        }
        return Collections.synchronizedMap(columns);
    }

    public boolean containsIndex(final String indexName) {
        return this.indexes.contains(indexName);
    }
}
