package com.zzx.dynamic.thread.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.sun.org.apache.bcel.internal.generic.LOOKUPSWITCH;
import com.zzx.dynamic.thread.executor.ConfigurableThreadPoolExecutor;
import com.zzx.dynamic.thread.executor.DdkyExecutor;
import com.zzx.dynamic.thread.queue.ResizableLinkedBlockingQueue;
import com.zzx.dynamic.thread.selector.DefaultExecutorSelector;
import com.zzx.dynamic.thread.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhouzhixiang
 * @Date 2021-04-09
 */
public class DdkyExecutorsProperty {

    private static final Logger LOGGER = LoggerFactory.getLogger(DdkyExecutorsProperty.class);

    private static final String DEFAULT_PROPRETIES_FILE_NAME = "ddky-executors.properties";

    private static final String DEFAULT_NAME_SPACE = "application";

    private static String NAME_SPACE_VALUE = DEFAULT_NAME_SPACE;

    private static final String PROPERTY_PREFIX = "ddky.executors.";
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

    private static Config config;

    private static String nameSpace;

    static {
        try {
            initialize();
            loadProps();
            listenApollo();
        } catch (Throwable ex) {
            LOGGER.warn("[DdkyExecutorsProperty] 解析失败", ex);
        }
    }

    /**
     * 监听apollo配置变化
     */
    public static void listenApollo() {

        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {

                for (String key : configChangeEvent.changedKeys()) {

                    if (!key.startsWith(PROPERTY_PREFIX)) {
                        if (LOGGER.isInfoEnabled()) {
                            LOGGER.info("当前配置修改项目不是叮当线程池配置参数，跳过，key = {}", key);
                        }
                        continue;
                    }

                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("叮当动态线程池配置发生了变化, 命名空间名 = {}, changedKey = {}", configChangeEvent.getNamespace(), key);
                    }

                    ConfigChange change = configChangeEvent.getChange(key);

                    String newValue = change.getNewValue();
                    refreshThreadPool(key, newValue);
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("叮当动态线程池配置发生了变化 key = {}, oldValue = {}, newValue = {}, changeType = {}", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());
                    }
                }
            }
        });
    }

    /**
     * 刷新线程池参数
     * @param key
     * @param newValue
     */
    private static void refreshThreadPool(String key, String newValue) {

        try {
            int index = matchIndex(key);

            String poolName = config.getProperty(EXECUTOR + "[" + index + "]" + "." + POOL_NAME, DdkyExecutorProperty.DEFAULT_POOL_NAME);

            DdkyExecutorProperty ddkyExecutorProperty = getDdkyExecutorProperty(poolName);

            if (ddkyExecutorProperty == null) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("[DdkyExecutorsProperty] refreshThreadPool 没有获取到线程池poolName = {}的线程配置", poolName);
                }
                return;
            }

            DdkyExecutor executor = ConfigurableThreadPoolExecutor.getCachedExecutorsMap().get(poolName);

            if (executor == null) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("[DdkyExecutorsProperty] refreshThreadPool 线程池执行对象为空，请注意, poolName name = {}", poolName);
                }
                return;
            }
            String corePoolSize = EXECUTOR + "[" + index + "]" + "." + CORE_POOL_SIZE;
            if (corePoolSize.equals(key)) {
                Integer coreSizeTemp = Integer.valueOf(newValue);
                if (coreSizeTemp > ddkyExecutorProperty.getMaximumPoolSize()) {
                    throw new IllegalArgumentException(String.format("核心线程数不能大于最大线程数 nameSpace = {}, coreSizeTemp = {}, maxPoolSize = {}", NAME_SPACE_VALUE, coreSizeTemp, ddkyExecutorProperty.getMaximumPoolSize()));
                }
                executor.setCorePoolSize(Integer.valueOf(newValue));
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("修改核心线程数 nameSpace = {}, key = {}, value = {}", NAME_SPACE_VALUE, key, newValue);
                }
            }
            String maximumPoolSize = EXECUTOR + "[" + index + "]" + "." + MAXIMUM_POOL_SIZE;
            if (maximumPoolSize.equals(key)) {
                executor.setMaximumPoolSize(Integer.valueOf(newValue));
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("修改最大线程数 nameSpace = {}, key = {}, value = {}", NAME_SPACE_VALUE, key, newValue);
                }
            }
            String keepAliveTime = EXECUTOR + "[" + index + "]" + "." + KEEP_ALIVE_TIME;
            if (keepAliveTime.equals(key)) {
                executor.setKeepAliveTime(Integer.valueOf(newValue), TimeUnit.SECONDS);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("修改活跃时间 nameSpace = {}, key = {}, value = {}", NAME_SPACE_VALUE, key, newValue);
                }
            }
            String queueCapacity = EXECUTOR + "[" + index + "]" + "." + QUEUE_CAPACITY;
            if (queueCapacity.equals(key)) {
                if (executor.getWorkQueue() instanceof ResizableLinkedBlockingQueue) {
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("当前队列是弹性队列，可被修改，nameSpace = {}, 修改前的队列长度：{}，修改后的队列长度：{}", NAME_SPACE_VALUE, executor.getWorkQueueCapacity(), newValue);
                    }
                    ((ResizableLinkedBlockingQueue<Runnable>) executor.getWorkQueue()).setCapacity(Integer.parseInt(newValue));
                } else {
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("线程池{}：当前队列不可被修改容量长度", poolName);
                    }
                }
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("[DdkyExecutorsProperty] refreshThreadPool 发生了异常 {}", e);
            }
        }

    }

    private static int matchIndex(String key) {
        Pattern p = Pattern.compile("\\[\\d+\\]");
        Matcher matcher = p.matcher(key);
        boolean b = matcher.find();
        int result;
        if (b) {
            result = Integer.parseInt(key.substring(key.lastIndexOf("[") + 1, key.lastIndexOf("]")));
        } else {
            throw new IllegalArgumentException(String.format("[DdkyExecutorsProperty] matchIndex key = %s, 未匹配到合法的配置参数", key));
        }
        return result;
    }

    public static void initialize() {
        try {
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_PROPRETIES_FILE_NAME));
            nameSpace = properties.getProperty(NAME_SPACE);
            nameSpace = Strings.isNotBlank(nameSpace) ? nameSpace : DEFAULT_NAME_SPACE;
            NAME_SPACE_VALUE = nameSpace;
            config = ConfigService.getConfig(props.getOrDefault(nameSpace, DEFAULT_NAME_SPACE));
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("[DdkyExecutorsProperty] 获取apollo配置异常, apollo配置名：{}", NAME_SPACE);
            }
            throw new RuntimeException("获取叮当动态线程池配置失败", e);
        }

        props.put(SELECTOR, DEFAULT_SELECTOR);
        props.put(METRICS_ENABLE, DEFAULT_METRICS_ENABLE);
        props.put(NAME_SPACE, DEFAULT_NAME_SPACE);
        DdkyExecutorProperty property = new DdkyExecutorProperty();
        EXECUTOR_PROPERTY_LIST.add(0, property);
    }

    public static void loadProps() throws IOException {

        //Properties properties = new Properties();
        //properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_PROPRETIES_FILE_NAME));
        //
        //String nameSpace = properties.getProperty(NAME_SPACE);
        //nameSpace = Strings.isNotBlank(nameSpace) ? nameSpace : DEFAULT_NAME_SPACE;

        //ConfigService.getConfig(PROPERTY_PREFIX + CORE_POOL_SIZE, );
        //Config config = null;
        //try {
        //    config = ConfigService.getConfig(props.getOrDefault(nameSpace, DEFAULT_NAME_SPACE));
        //} catch (Exception e) {
        //    if (LOGGER.isErrorEnabled()) {
        //        LOGGER.error("[DdkyExecutorsProperty] 获取apollo配置异常, apollo配置名：{}", NAME_SPACE);
        //    }
        //}

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
                property.addProperty(POOL_NAME, config.getProperty(EXECUTOR + "[" + index + "]" + "." + POOL_NAME, DdkyExecutorProperty.DEFAULT_POOL_NAME));
                property.addProperty(CORE_POOL_SIZE, config.getProperty(EXECUTOR + "[" + index + "]" + "." + CORE_POOL_SIZE, String.valueOf(DdkyExecutorProperty.DEFAULT_CORE_POOL_SIZE)));
                property.addProperty(MAXIMUM_POOL_SIZE, config.getProperty(EXECUTOR + "[" + index + "]" + "." + MAXIMUM_POOL_SIZE, String.valueOf(DdkyExecutorProperty.DEFAULT_MAXIMUM_POOL_SIZE)));
                property.addProperty(KEEP_ALIVE_TIME, config.getProperty(EXECUTOR + "[" + index + "]" + "." + KEEP_ALIVE_TIME, String.valueOf(DdkyExecutorProperty.DEFAULT_KEEP_ALIVE_TIME)));
                property.addProperty(QUEUE_CAPACITY, config.getProperty(EXECUTOR + "[" + index + "]" + "." + QUEUE_CAPACITY, String.valueOf(DdkyExecutorProperty.DEFAULT_QUEUE_CAPACITY)));
                property.addProperty(WORK_QUEUE_TYPE, config.getProperty(EXECUTOR + "[" + index + "]" + "." + WORK_QUEUE_TYPE, DdkyExecutorProperty.DEFAULT_WORK_QUEUE_TYPE));
                property.addProperty(REJECTED_HANDLER_TYPE, config.getProperty(EXECUTOR + "[" + index + "]" + "." + REJECTED_HANDLER_TYPE, DdkyExecutorProperty.DEFAULT_REJECTED_HANDLER_TYPE));
                property.addProperty(SELECTOR_EXPRESSION, config.getProperty(EXECUTOR + "[" + index + "]" + "." + SELECTOR_EXPRESSION, DdkyExecutorProperty.DEFAULT_SELECTOR_EXPRESSION));
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

    /**
     * 是否允许监控
     * @return
     */
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
