package com.api.config.masterslave;

import com.api.config.TypeBasedSPIConfiguration;
import lombok.Getter;

import java.util.Properties;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
@Getter
public final class LoadBalanceStrategyConfiguration extends TypeBasedSPIConfiguration {

    public LoadBalanceStrategyConfiguration(final String type) {
        super(type);
    }

    public LoadBalanceStrategyConfiguration(final String type, final Properties properties) {
        super(type, properties);
    }
}
