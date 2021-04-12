package com.zzx.shardingsphere.constant.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
@RequiredArgsConstructor
@Getter
public enum ShardingPropertiesConstant {

    SQL_SHOW("sql.show", String.valueOf(Boolean.FALSE), boolean.class),

    SQL_SIMPLE("sql.simple", String.valueOf(Boolean.FALSE), boolean.class),

    ACCEPTOR_SIZE("acceptor.size", String.valueOf(Runtime.getRuntime().availableProcessors() * 2), int.class),

    EXECUTOR_SIZE("executor.size", String.valueOf(0), int.class),

    MAX_CONNECTIONS_SIZE_PER_QUERY("max.connections.size.per.query", String.valueOf(1), int.class),

    QUERY_WITH_CIPHER_COLUMN("query.with.cipher.column", String.valueOf(Boolean.class), boolean.class),

    PROXY_FRONTEND_FLUSH_THRESHOLD("proxy.frontend.flush.threshold", String.valueOf(128), int.class),

    PROXY_TRANSACTION_TYPE("proxy.transaction.type", "LOCAL", String.class),

    PROXY_OPENTRACING_ENABLED("proxy.opentracing.enabled", String.valueOf(Boolean.FALSE), boolean.class),

    PROXY_HINT_ENABLED("proxy.hint.enabled", String.valueOf(Boolean.FALSE), boolean.class),

    PROXY_BACKEND_MAX_CONNECTIONS("proxy.backend.max.connections", String.valueOf(8), int.class),

    PROXY_BACKEND_CONNECTION_TIMEOUT_SECONDS("proxy.backend.connection.timeout.seconds", String.valueOf(60), int.class),

    CHECK_TABLE_METADATA_ENABLED("check.table.metadata.enabled", String.valueOf(Boolean.FALSE), boolean.class);

    private final String key;

    private final String defaultValue;

    private final Class<?> type;

    public static ShardingPropertiesConstant findByKey(final String key) {
        for (ShardingPropertiesConstant each : ShardingPropertiesConstant.values()) {
            if (each.getKey().equals(key)) {
                return each;
            }
        }
        return null;
    }
}
