package com.zzx.shardingsphere.metadata;

import com.zzx.shardingsphere.metadata.datasource.DataSourceMetas;
import com.zzx.shardingsphere.metadata.table.TableMetas;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
@RequiredArgsConstructor
@Getter
public final class ShardingSphereMetaData {

    private final DataSourceMetas dataSources;

    private final TableMetas tables;
}
