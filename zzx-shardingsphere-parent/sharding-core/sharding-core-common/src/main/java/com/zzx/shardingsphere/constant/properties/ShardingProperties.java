package com.zzx.shardingsphere.constant.properties;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.zzx.shardingsphere.util.StringUtil;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
public final class ShardingProperties {

    @Getter
    private final Properties props;

    private final Map<ShardingPropertiesConstant, Object> cachedProperties = new ConcurrentHashMap<>();

    public ShardingProperties(final Properties props) {
        this.props = props;
        this.valid();
    }

    private void valid() {
        Set<String> propertyNames = props.stringPropertyNames();
        Collection<String> errorMessages = new ArrayList<>(propertyNames.size());;
        for (String each : propertyNames) {
            ShardingPropertiesConstant shardingPropertiesConstant = ShardingPropertiesConstant.findByKey(each);
            if (null == shardingPropertiesConstant) {
                continue;
            }
            Class<?> type = shardingPropertiesConstant.getType();
            String value = props.getProperty(each);
            if (type == boolean.class && !StringUtil.isBooleanValue(value)) {
                errorMessages.add(getErrorMessage(shardingPropertiesConstant, value));
            } else if (type == int.class && !StringUtil.isIntValue(value)) {
                errorMessages.add(getErrorMessage(shardingPropertiesConstant, value));
            }
        }
        if (!errorMessages.isEmpty()) {
            throw new IllegalArgumentException(Joiner.on(" ").join(errorMessages));
        }
    }

    private String getErrorMessage(final ShardingPropertiesConstant shardingPropertiesConstant, final String value) {
        return String.format("Value '%s' of '%s' cannot convert to type '%s'", value, shardingPropertiesConstant.getKey(), shardingPropertiesConstant.getType());
    }

    public <T> T getValue(final ShardingPropertiesConstant shardingPropertiesConstant) {
        if (cachedProperties.containsKey(shardingPropertiesConstant)) {
            return (T) cachedProperties.get(shardingPropertiesConstant);
        }
        String value = props.getProperty(shardingPropertiesConstant.getKey());
        if (Strings.isNullOrEmpty(value)) {
            Object obj = props.get(shardingPropertiesConstant.getKey());
            if (null == obj) {
                value = shardingPropertiesConstant.getDefaultValue();
            } else {
                value = obj.toString();
            }
        }
        Object result;
        if (boolean.class == shardingPropertiesConstant.getType()) {
            result = Boolean.valueOf(value);
        } else if (int.class == shardingPropertiesConstant.getType()) {
            result = Integer.valueOf(value);
        } else if (long.class == shardingPropertiesConstant.getType()) {
            result = Long.valueOf(value);
        } else {
            result = value;
        }
        cachedProperties.put(shardingPropertiesConstant, result);
        return (T) result;
    }
}
