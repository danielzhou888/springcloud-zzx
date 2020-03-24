package com.api.hint;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HintManager implements AutoCloseable {

    private static final ThreadLocal<HintManager> HINT_MANAGER_HOLDER = new ThreadLocal<>();

    private final Multimap<String, Comparable<?>> databaseShardingValues = HashMultimap.create();

    private final Multimap<String, Comparable<?>> tableShardingValues = HashMultimap.create();

    private boolean databaseShardingOnly;

    private boolean masterRouteOnly;

    public static HintManager getInstance() {
        Preconditions.checkState(null == HINT_MANAGER_HOLDER.get(), "Hint has previous value, please clear first.");
        HintManager hint = new HintManager();
        HINT_MANAGER_HOLDER.set(hint);
        return hint;
    }

    public void setDatabaseShardingValues(final Comparable<?> value) {
        databaseShardingValues.clear();
        tableShardingValues.clear();
        databaseShardingValues.put("", value);
        databaseShardingOnly = true;
    }

    public void addDatableShardingValue(final String logicTable, final Comparable<?> value) {
        if (databaseShardingOnly) {
            databaseShardingValues.removeAll("");
        }
        databaseShardingValues.put(logicTable, value);
        databaseShardingOnly = false;
    }

    public void addTableShardingValue(final String logicTable, final Comparable<?> value) {
        if (databaseShardingOnly) {
            databaseShardingValues.removeAll("");
        }
        tableShardingValues.put(logicTable, value);
        databaseShardingOnly = false;
    }

    public static Collection<Comparable<?>> getDatabaseShardingValues() {
        return getDatabaseShardingValues("");
    }

    public static Collection<Comparable<?>> getDatabaseShardingValues(final String logicTable) {
        return null == HINT_MANAGER_HOLDER.get() ? Collections.EMPTY_LIST : HINT_MANAGER_HOLDER.get().databaseShardingValues.get(logicTable);
    }

    public static Collection<Comparable<?>> getTableShardingValues(final String logicTable) {
        return null == HINT_MANAGER_HOLDER.get() ? Collections.EMPTY_LIST : HINT_MANAGER_HOLDER.get().tableShardingValues.get(logicTable);
    }

    public static boolean isDatabaseShardingOnly() {
        return null != HINT_MANAGER_HOLDER.get() && HINT_MANAGER_HOLDER.get().databaseShardingOnly;
    }

    public void setMasterRouteOnly() {
        masterRouteOnly = true;
    }

    public static boolean isMasterRouteOnly() {
        return null != HINT_MANAGER_HOLDER.get() && HINT_MANAGER_HOLDER.get().masterRouteOnly;
    }

    public static void clear() {
        HINT_MANAGER_HOLDER.remove();
    }

    @Override
    public void close() throws Exception {
        HintManager.clear();
    }
}
