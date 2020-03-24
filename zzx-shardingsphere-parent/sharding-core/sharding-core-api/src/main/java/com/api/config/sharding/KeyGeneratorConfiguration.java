package com.api.config.sharding;

import com.api.config.TypeBasedSPIConfiguration;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.Getter;

import java.util.Properties;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
@Getter
public class KeyGeneratorConfiguration extends TypeBasedSPIConfiguration {

    private final String colum;

    public KeyGeneratorConfiguration(final String type, final String colum) {
        super(type);
        this.colum = colum;
    }

    public KeyGeneratorConfiguration(final String type, final Properties properties, final String colum) {
        super(type, properties);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(colum), "Column is required");
        this.colum = colum;
    }
}
