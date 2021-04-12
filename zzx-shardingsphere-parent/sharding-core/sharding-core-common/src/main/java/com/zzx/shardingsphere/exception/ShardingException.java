package com.zzx.shardingsphere.exception;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
public class ShardingException extends RuntimeException {

    private static final long serialVersionUID = 2407215704351334565L;

    public ShardingException(final String errorMessage, final Object... args) {
        super(String.format(errorMessage, args));
    }

    public ShardingException(final String message, final Exception cause) {
        super(message, cause);
    }

    public ShardingException(final Exception cause) {
        super(cause);
    }
}
