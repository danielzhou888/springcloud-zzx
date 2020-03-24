package com.zzx.shardingsphere.config;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
public final class ShardingConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 7645380374354179473L;

    public ShardingConfigurationException(final String errorMessage, final Object... args) {
        super(String.format(errorMessage, args));
    }

    public ShardingConfigurationException(final Exception cause) {
        super(cause);
    }
}
