package com.zzx.shardingsphere.metadata.table;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
public final class TableMetas {

    private final Map<String, TableMetaData> tables;

    public TableMetas(final Map<String, TableMetaData> tables) {
        this.tables = new ConcurrentHashMap<>(tables);
    }

    public TableMetaData get(final String logicTableName) {
        return tables.get(logicTableName);
    }

    public void put(final String logicTableName, final TableMetaData tableMetaData) {
        tables.put(logicTableName, tableMetaData);
    }

    public void remove(final String logicTableName) {
        tables.remove(logicTableName);
    }

    public boolean containsTable(final String tableName) {
        return tables.containsKey(tableName);
    }

    public boolean containsColumn(final String tableName, final String column) {
        return this.containsTable(tableName) && tables.get(tableName).getColumns().keySet().contains(column.toLowerCase());
    }

    public List<String> getAllColumnNames(final String tableName) {
        return tables.containsKey(tableName) ? new ArrayList<>(tables.get(tableName).getColumns().keySet()) : Collections.emptyList();
    }

    public Collection<String> getAllTableNames() {
        return tables.keySet();
    }
}
