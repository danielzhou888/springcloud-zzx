package com.zzx.dynamic.thread.factory;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.zzx.dynamic.enums.ApolloKeyEnum;
import com.zzx.dynamic.queue.ResizableLinkedBlockingQueue;
import com.zzx.dynamic.utils.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 动态线程池工厂类
 * @author zhouzhixiang
 * @Date 2021-04-09
 */
public class DynamicThreadPoolFactory {

    private static final Logger logger = LoggerFactory.getLogger(DynamicThreadPoolFactory.class);

    /** apollo命名空间 **/
    private String nameSpace;

    /** 线程执行器 **/
    private volatile ThreadPoolExecutor executor;

    /** 核心线程数 **/
    private Integer coreSize = 10;

    /** 最大线程数 **/
    private Integer maxPoolSize = 20;

    /** 等待队列长度 **/
    private Integer queueSize = 200;

    /** 线程存活时间 默认1秒 **/
    private Long keepAliveTime = 1L;

    /** 线程名 **/
    private String threadPrefixName = "default";

    private String abortPolicy= "AbortPolicy";

    public DynamicThreadPoolFactory(String nameSpace) {
        this.nameSpace = nameSpace;
        Config config = ConfigService.getConfig(nameSpace);
        initThreadPool(config);
        listenApollo(config);
    }

    /**
     * 初始化
     * @param config
     */
    private void initThreadPool(Config config) {
        if (executor == null) {
            synchronized (DynamicThreadPoolFactory.class) {
                if (executor == null) {
                    String coreSizeTemp = config.getProperty(ApolloKeyEnum.CORE_SIZE.getKey(), coreSize.toString());
                    String maxSizeTemp = config.getProperty(ApolloKeyEnum.MAX_SIZE.getKey(), maxPoolSize.toString());
                    String keepAliveTimeTemp = config.getProperty(ApolloKeyEnum.KEEP_ALIVE_TIME.getKey(), keepAliveTime.toString());
                    String queueSizeTemp = config.getProperty(ApolloKeyEnum.QUEUE_SIZE.getKey(), queueSize.toString());
                    String rejectPolicyNameTemp = config.getProperty(ApolloKeyEnum.REFUSE_POLICY_NAME.getKey(), abortPolicy);
                    String threadPrefixNameTemp = config.getProperty(ApolloKeyEnum.THREAD_PREFIX_NAME.getKey(), threadPrefixName);
                    RejectedExecutionHandler rejectedExecutionHandler = this.getInstance(rejectPolicyNameTemp);
                    executor = new ThreadPoolExecutor(Integer.valueOf(coreSizeTemp), Integer.valueOf(maxSizeTemp), Integer.valueOf(keepAliveTimeTemp), TimeUnit.SECONDS, new ResizableLinkedBlockingQueue<Runnable>(Integer.valueOf(queueSizeTemp)),
                            new NamedThreadFactory(threadPrefixNameTemp, false), rejectedExecutionHandler);
                }
            }
        }
    }

    /**
     * 监听器
     * @param config
     */
    public void listenApollo(Config config) {
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {
                if (logger.isInfoEnabled()) {
                    logger.info("动态线程池配置发生了变化, 命名空间名 = {}", configChangeEvent.getNamespace());
                }
                for (String key : configChangeEvent.changedKeys()) {
                    ConfigChange change = configChangeEvent.getChange(key);
                    String newValue = change.getNewValue();
                    refreshThreadPool(key, newValue);
                    if (logger.isInfoEnabled()) {
                        logger.info("动态线程池配置发生了变化 key = {}, oldValue = {}, newValue = {}, changeType = {}", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType());
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
    private void refreshThreadPool(String key, String newValue) {
        if (executor == null) {
            if (logger.isWarnEnabled()) {
                logger.warn("线程池对象为空，请注意, nameSpace name = {}", nameSpace);
            }
            return;
        }
        if (ApolloKeyEnum.CORE_SIZE.getKey().equals(key)) {
            Integer coreSizeTemp = Integer.valueOf(newValue);
            if (coreSizeTemp > maxPoolSize) {
                throw new IllegalArgumentException(String.format("核心线程数不能大于最大线程数 nameSpace = {}, coreSizeTemp = {}, maxPoolSize = {}", nameSpace, coreSizeTemp, maxPoolSize));
            }
            executor.setCorePoolSize(Integer.valueOf(newValue));
            if (logger.isInfoEnabled()) {
                logger.info("修改核心线程数 nameSpace = {}, key = {}, value = {}", nameSpace, key, newValue);
            }
        }
        if (ApolloKeyEnum.MAX_SIZE.getKey().equals(key)) {
            executor.setMaximumPoolSize(Integer.valueOf(newValue));
            if (logger.isInfoEnabled()) {
                logger.info("修改最大线程数 nameSpace = {}, key = {}, value = {}", nameSpace, key, newValue);
            }
        }
        if (ApolloKeyEnum.KEEP_ALIVE_TIME.getKey().equals(key)) {
            executor.setKeepAliveTime(Integer.valueOf(newValue), TimeUnit.SECONDS);
            if (logger.isInfoEnabled()) {
                logger.info("修改活跃时间 nameSpace = {}, key = {}, value = {}", nameSpace, key, newValue);
            }
        }
        if (ApolloKeyEnum.QUEUE_SIZE.getKey().equals(key)) {
        }
    }



    private <T> T getInstance(String className) {
        T result = null;
        try {
            Class<?> aClass = Class.forName(className);
            result = (T) aClass.newInstance();
        } catch (ClassNotFoundException e) {
            logger.error(String.format("动态线程获取反射类失败，没有找到您定义的阻塞队列类 className = %s, %s", className, e));
        } catch (IllegalAccessException e) {
            logger.error(String.format("动态线程获取反射类失败，非法操作 className = %s, %s", className, e));
        } catch (InstantiationException e) {
            logger.error(String.format("动态线程获取反射类失败 className = %s, %s", className, e));
        }
        return result;
    }
}
