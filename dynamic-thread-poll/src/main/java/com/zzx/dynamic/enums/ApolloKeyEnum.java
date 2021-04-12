package com.zzx.dynamic.enums;

/**
 * Apollo 动态线程池 key枚举
 * @author zhouzhixiang
 * @Date 2021-04-09
 */
public enum ApolloKeyEnum {

    CORE_SIZE("coreSize", "核心线程池"),
    MAX_SIZE("maxSize", "最大线程数"),
    KEEP_ALIVE_TIME("keepAliveTime", "线程活跃时间"),
    QUEUE_SIZE("queueSize", "队列大小"),
    REFUSE_POLICY_NAME("rejectPolicyName", "拒绝策略类名"),
    THREAD_PREFIX_NAME("threadPrefixName", "线程名前缀");

    private String key;
    private String value;

    ApolloKeyEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
