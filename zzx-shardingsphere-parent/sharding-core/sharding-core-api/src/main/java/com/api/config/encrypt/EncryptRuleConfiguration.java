package com.api.config.encrypt;

import com.api.config.RuleConfiguration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-03-23
 */
@RequiredArgsConstructor
@Getter
public final class EncryptRuleConfiguration implements RuleConfiguration {

    private final Map<String, EncryptorRuleConfiguration> encryptors;

    private final Map<String, EncryptTableRuleConfiguration> tables;

    public EncryptRuleConfiguration() {
        this(new LinkedHashMap<String, EncryptorRuleConfiguration>(), new LinkedHashMap<String, EncryptTableRuleConfiguration>());
    }
}
