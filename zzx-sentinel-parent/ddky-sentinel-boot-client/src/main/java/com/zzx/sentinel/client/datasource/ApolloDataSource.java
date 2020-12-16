package com.zzx.sentinel.client.datasource;

import com.alibaba.csp.sentinel.datasource.AbstractDataSource;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.property.SentinelProperty;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.utils.StringUtils;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.zzx.sentinel.client.log.RecordLog;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ApolloDataSource<T> extends AbstractDataSource<String, T> {
    private final Config config;
    private String ruleKey;
    private final String defaultRuleValue;
    private ConfigChangeListener configChangeListener;
    private final String ruleKeySuffix;
    private final Set<String> ruleKeyList;

    public ApolloDataSource(String namespaceName, String ruleKeySuffix, String defaultRuleValue, Converter<String, T> parser) {
        super(parser);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(namespaceName), "Namespace name could not be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(ruleKeySuffix), "ruleKeySuffix could not be null or empty!");
        this.ruleKeySuffix = ruleKeySuffix;
        this.defaultRuleValue = defaultRuleValue;
        this.config = ConfigService.getConfig(namespaceName);
        Set<String> allRuleKeys = this.config.getPropertyNames();
        if (allRuleKeys != null && allRuleKeys.size() > 0) {
            ruleKeyList = allRuleKeys.stream().filter(key -> key.startsWith(ruleKeySuffix)).collect(Collectors.toSet());
        } else {
            ruleKeyList = new HashSet<>();
        }

        this.initialize();
        RecordLog.info(String.format("Initialized rule for namespace: %s, rule key: %s", namespaceName, ruleKeySuffix), new Object[0]);
    }

    private void initialize() {
        this.initializeConfigChangeListener();
        try {
            for (String s : ruleKeyList) {
                this.loadAndUpdateRules(s);
            }
        } catch (Exception e) {
            RecordLog.error("[ApolloDataSource] Error when initialize rule config {}", e);
        }
    }

    private void loadAndUpdateRules(String key) {
        try {
            String value = this.config.getProperty(key, this.defaultRuleValue);
            T newValue = this.loadConfig(value);
            if (newValue == null) {
                RecordLog.warn("[ApolloDataSource] WARN: rule config is null, you may have to check your data source", new Object[0]);
            }
            SentinelProperty<T> property = this.getProperty();
            property.updateValue(newValue);
        } catch (Exception e) {
            RecordLog.warn("[ApolloDataSource] Error when loading rule config", e);
        }
    }

    private void loadAndUpdateRules() {
        try {
            T newValue = this.loadConfig();
            if (newValue == null) {
                RecordLog.warn("[ApolloDataSource] WARN: rule config is null, you may have to check your data source", new Object[0]);
            }

            this.getProperty().updateValue(newValue);
        } catch (Throwable var2) {
            RecordLog.warn("[ApolloDataSource] Error when loading rule config", var2);
        }
    }

    private void loadAndUpdateRulesRuleKeySuffix(Set<String> changedKeys) {
        for (String changedKey : changedKeys) {
            this.loadAndUpdateRules(changedKey);
        }
    }

    private void initializeConfigChangeListener() {
        this.configChangeListener = new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                Set<String> changedKeys = changeEvent.changedKeys();
                RecordLog.info("[ApolloDataSource] Received config changes key：{}", StringUtils.join(changedKeys, ","));
                ApolloDataSource.this.loadAndUpdateRulesRuleKeySuffix(changedKeys);
            }
        };
        this.config.addChangeListener(this.configChangeListener, null, Sets.newHashSet(new String[]{ruleKeySuffix}));
    }



    @Override
    public String readSource() throws Exception {
        return this.config.getProperty(this.ruleKey, this.defaultRuleValue);
    }

    @Override
    public void close() throws Exception {
        this.config.removeChangeListener(this.configChangeListener);
    }
}
