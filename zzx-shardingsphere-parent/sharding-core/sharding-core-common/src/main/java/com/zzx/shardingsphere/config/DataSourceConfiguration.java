package com.zzx.shardingsphere.config;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author zhouzhixiang
 * @Date 2020-03-24
 */
@RequiredArgsConstructor
@Getter
public final class DataSourceConfiguration {

    private static final String GETTER_PREFIX = "get";

    private static final String SETTER_PREFIX = "set";

    private static final Collection<Class<?>> GENERAL_CLASS_TYPE;

    private static final Collection<String> SKIPPED_PROPERTY_NAMES;

    static {
        GENERAL_CLASS_TYPE = Sets.<Class<?>>newHashSet(boolean.class, Boolean.class, int.class, Integer.class, long.class, Long.class, String.class);
        SKIPPED_PROPERTY_NAMES = Sets.newHashSet("loginTimeout");
    }

    private final String dataSourceClassName;

    private final Map<String, Object> properties = new LinkedHashMap<>();

    public static DataSourceConfiguration getDataSourceConfiguration(final DataSource dataSource) {
        DataSourceConfiguration result = new DataSourceConfiguration(dataSource.getClass().getName());
        result.getProperties().putAll(findAllGetterproperties(dataSource));
        return result;
    }

    @SneakyThrows
    private static Map<String, Object> findAllGetterproperties(final Object target) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (Method each : findAllGetterMethods(target.getClass())) {
            String propertyName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, each.getName().substring(GETTER_PREFIX.length()));
            if (GENERAL_CLASS_TYPE.contains(each.getReturnType()) && !SKIPPED_PROPERTY_NAMES.contains(propertyName)) {
                result.put(propertyName, each.invoke(target));
            }
        }
        return result;
    }

    private static Collection<Method> findAllGetterMethods(final Class<?> clazz) {
        Collection<Method> result = new HashSet<>();
        for (Method each : clazz.getMethods()) {
            if (each.getName().startsWith(GETTER_PREFIX) && 0 == each.getParameterTypes().length) {
                result.add(each);
            }
        }
        return result;
    }

    @SneakyThrows
    public DataSource createDataSource() {
        DataSource result = (DataSource) Class.forName(dataSourceClassName).newInstance();
        Method[] methods = result.getClass().getMethods();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            if (SKIPPED_PROPERTY_NAMES.contains(entry.getKey())) {
                continue;
            }
            Optional<Method> setterMethod = findSetterMethod(methods, entry.getKey());
            if (setterMethod.isPresent()) {
                setterMethod.get().invoke(result, entry.getValue());
            }
        }
        return result;
    }

    private Optional<Method> findSetterMethod(final Method[] methods, final String property) {
        String setterMethodName = Joiner.on("").join(SETTER_PREFIX, CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, property));
        for (Method each : methods) {
            if (each.getName().equals(setterMethodName) && 1 == each.getParameterTypes().length) {
                return Optional.of(each);
            }
        }
        return Optional.absent();
    }

    @Override
    public boolean equals(final Object o) {
        return this == o || null != o && getClass() == o.getClass() && equalsByProperties((DataSourceConfiguration) o);
    }

    private boolean equalsByProperties(final DataSourceConfiguration dataSourceConfiguration) {
        if (!this.dataSourceClassName.equals(dataSourceConfiguration.getDataSourceClassName())) {
            return false;
        }
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            if (!String.valueOf(entry.getValue()).equals(String.valueOf(dataSourceConfiguration.getProperties().get(entry.getKey())))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            sb.append(entry.getKey()).append(entry.getValue().toString());
        }
        return Objects.hash(dataSourceClassName, sb.toString());
    }

    public void addAlias(final String... alias) {
        Object value = null;
        for (String each : alias) {
            if (null != properties.get(each)) {
                value = properties.get(each);
            }
        }
        if (null == value) {
            return;
        }
        for (String each : alias) {
            properties.put(each, value);
        }
    }

}
