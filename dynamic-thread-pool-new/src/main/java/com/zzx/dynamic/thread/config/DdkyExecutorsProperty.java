package com.zzx.dynamic.thread.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.zzx.dynamic.thread.selector.DefaultExecutorSelector;
import com.zzx.dynamic.thread.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author zhouzhixiang
 * @Date 2021-04-09
 */
public class DdkyExecutorsProperty {

    private static final Logger LOGGER = LoggerFactory.getLogger(DdkyExecutorsProperty.class);

    private static final String DEFAULT_PROPRETIES_FILE_NAME = "ddky-executors.properties";

    private static final String DEFAULT_NAME_SPACE = "application";

    private static final String PROPERTY_PREFIX = "ddky.executors.";;
    private static final String SELECTOR = PROPERTY_PREFIX + "selector";
    private static final String METRICS_ENABLE = PROPERTY_PREFIX + "metricsEnable";
    private static final String EXECUTOR = PROPERTY_PREFIX + "executor";

    private static final String DEFAULT_SELECTOR = DefaultExecutorSelector.class.getName();

    private static final String DEFAULT_METRICS_ENABLE = "false";

    private static final List<DdkyExecutorProperty> EXECUTOR_PROPERTY_LIST = new ArrayList<>();
    private static final Map<String, String > props = new ConcurrentHashMap<>();

    private static final String POOL_NAME = "poolName";
    private static final String CORE_POOL_SIZE = "corePoolSize";
    private static final String MAXIMUM_POOL_SIZE = "maximumPoolSize";
    private static final String KEEP_ALIVE_TIME = "keepAliveTime";
    private static final String QUEUE_CAPACITY = "queueCapacity";
    private static final String WORK_QUEUE_TYPE = "workQueueType";
    private static final String REJECTED_HANDLER_TYPE = "rejectedHandlerType";
    private static final String SELECTOR_EXPRESSION = "expression";
    private static final String NAME_SPACE = PROPERTY_PREFIX + "nameSpace";

    public DdkyExecutorsProperty() {
        try {
            initialize();
            loadProps();
        } catch (Throwable ex) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("[DdkyExecutorsProperty] 解析失败", ex);
            }
        }
    }

    static {
        try {
            initialize();
            loadProps();
        } catch (Throwable ex) {
            LOGGER.warn("[DdkyExecutorsProperty] 解析失败", ex);
        }
    }

    //public DdkyExecutorsProperty(String nameSpace) {
    //    try {
    //        initialize();
    //        props.put(NAME_SPACE, nameSpace);
    //        loadProps();
    //    } catch (Throwable ex) {
    //        if (LOGGER.isWarnEnabled()) {
    //            LOGGER.warn("[DdkyExecutorsProperty] 解析失败", ex);
    //        }
    //    }
    //}

    //public void init() {
    //    try {
    //        initialize();
    //        loadProps();
    //    } catch (Throwable ex) {
    //        if (LOGGER.isWarnEnabled()) {
    //            LOGGER.warn("[DdkyExecutorsProperty] 解析失败", ex);
    //        }
    //    }
    //}

    public static void initialize() {
        props.put(SELECTOR, DEFAULT_SELECTOR);
        props.put(METRICS_ENABLE, DEFAULT_METRICS_ENABLE);
        props.put(NAME_SPACE, DEFAULT_NAME_SPACE);
        DdkyExecutorProperty property = new DdkyExecutorProperty();
        EXECUTOR_PROPERTY_LIST.add(0, property);
    }

    public static void loadProps() throws IOException {

        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_PROPRETIES_FILE_NAME));

        String nameSpace = properties.getProperty(NAME_SPACE);
        nameSpace = Strings.isNotBlank(nameSpace) ? nameSpace : DEFAULT_NAME_SPACE;

        //ConfigService.getConfig(PROPERTY_PREFIX + CORE_POOL_SIZE, );
        Config config = null;
        try {
            config = ConfigService.getConfig(props.getOrDefault(nameSpace, DEFAULT_NAME_SPACE));
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("[DdkyExecutorsProperty] 获取apollo配置异常, apollo配置名：{}", NAME_SPACE);
            }
        }

        String selector = config.getProperty(SELECTOR, DEFAULT_SELECTOR);
        if (Strings.isNotBlank(selector)) {
            props.put(SELECTOR, selector);
        }
        String metricsEnable = config.getProperty(METRICS_ENABLE, DEFAULT_METRICS_ENABLE);
        if (Strings.isNotBlank(metricsEnable)) {
            props.put(METRICS_ENABLE, metricsEnable);
        }
        Pattern pattern = Pattern.compile("[^0-9]");
        Set<String> counts = new HashSet<>();
        Set<String> propertyNames = config.getPropertyNames();
        for (String key : propertyNames) {
            if (key.startsWith(EXECUTOR)) {
                String numberKey = pattern.matcher(key).replaceAll("");
                if (Strings.isNotBlank(numberKey)) {
                    counts.add(numberKey);
                }
            }
        }

        if (counts.size() > 0) {
            for (String index : counts) {
                DdkyExecutorProperty property = new DdkyExecutorProperty();
                property.addProperty(POOL_NAME, config.getProperty(EXECUTOR + "[" + index + "]" + "," + POOL_NAME, DdkyExecutorProperty.DEFAULT_POOL_NAME));
                property.addProperty(CORE_POOL_SIZE, config.getProperty(EXECUTOR + "[" + index + "]" + "," + CORE_POOL_SIZE, DdkyExecutorProperty.DEFAULT_POOL_NAME));
                property.addProperty(MAXIMUM_POOL_SIZE, config.getProperty(EXECUTOR + "[" + index + "]" + "," + MAXIMUM_POOL_SIZE, String.valueOf(DdkyExecutorProperty.DEFAULT_MAXIMUM_POOL_SIZE)));
                property.addProperty(KEEP_ALIVE_TIME, config.getProperty(EXECUTOR + "[" + index + "]" + "," + KEEP_ALIVE_TIME, String.valueOf(DdkyExecutorProperty.DEFAULT_KEEP_ALIVE_TIME)));
                property.addProperty(QUEUE_CAPACITY, config.getProperty(EXECUTOR + "[" + index + "]" + "," + QUEUE_CAPACITY, String.valueOf(DdkyExecutorProperty.DEFAULT_QUEUE_CAPACITY)));
                property.addProperty(WORK_QUEUE_TYPE, config.getProperty(EXECUTOR + "[" + index + "]" + "," + WORK_QUEUE_TYPE, DdkyExecutorProperty.DEFAULT_WORK_QUEUE_TYPE));
                property.addProperty(REJECTED_HANDLER_TYPE, config.getProperty(EXECUTOR + "[" + index + "]" + "," + REJECTED_HANDLER_TYPE, DdkyExecutorProperty.DEFAULT_REJECTED_HANDLER_TYPE));
                property.addProperty(SELECTOR_EXPRESSION, config.getProperty(EXECUTOR + "[" + index + "]" + "," + REJECTED_HANDLER_TYPE, DdkyExecutorProperty.DEFAULT_SELECTOR_EXPRESSION));
                EXECUTOR_PROPERTY_LIST.add(Integer.parseInt(index), property);
            }
        }
    }

    public String getSelector() {
        return props.get(SELECTOR);
    }

    public static List<DdkyExecutorProperty> getDdkyExecutorPropertyList() {
        return EXECUTOR_PROPERTY_LIST;
    }

    public static DdkyExecutorProperty getDdkyExecutorProperty(String poolName) {
        int index = EXECUTOR_PROPERTY_LIST.indexOf(DdkyExecutorProperty.named(poolName));
        return index != -1 ? EXECUTOR_PROPERTY_LIST.get(index) : null;
    }

    public boolean isMetricsEnable() {
        return Boolean.parseBoolean(props.get(METRICS_ENABLE));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("DdkyExecutorsProperty");
        builder.append("selector=").append(getSelector()).append(", ");
        builder.append("metricsEnable=").append(isMetricsEnable()).append(", ");
        for (DdkyExecutorProperty property : EXECUTOR_PROPERTY_LIST) {
            builder.append(property.toString());
        }
        builder.append("]");
        return builder.toString();
    }
}
