package com.api.config.encrypt;

import com.api.config.TypeBasedSPIConfiguration;
import lombok.Getter;

import java.util.Properties;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
@Getter
public final class EncryptorRuleConfiguration extends TypeBasedSPIConfiguration {

    public EncryptorRuleConfiguration(final String type, final Properties properties) {
        super(type, properties);
    }
}
