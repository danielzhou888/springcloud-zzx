package com.zzx.shardingsphere.metadata.datasource.exception;

import com.zzx.shardingsphere.exception.ShardingException;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
public final class UnrecognizedDatabaseURLException extends ShardingException {

    private static final long serialVersionUID = 4189362647032671316L;

    public UnrecognizedDatabaseURLException(final String url, final String pattern) {
        super(String.format("The URL: '%s' is not recognized. Please refer to this pattern: 's'.", url, pattern));
    }
}
