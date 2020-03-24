package com.api.config;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.Getter;

import java.util.Properties;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
@Getter
public abstract class TypeBasedSPIConfiguration {

    private final String type;

    private final Properties properties;

    public TypeBasedSPIConfiguration(final String type) {
        this(type, null);
    }

    public TypeBasedSPIConfiguration(String type, Properties properties) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(type), "Name is required");
        this.type = type;
        this.properties = properties == null ? new Properties() : properties;
    }
}
