package com.api.config.encrypt;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
@NoArgsConstructor
@Getter
public final class EncryptTableRuleConfiguration {

    private final Map<String, EncryptColumnRuleConfiguration> columns = new LinkedHashMap<>();

    public EncryptTableRuleConfiguration(final Map<String, EncryptColumnRuleConfiguration> columns) {
        this.columns.putAll(columns);
    }
}
