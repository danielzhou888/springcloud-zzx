package com.zzx.sentinel.client.util;

/**
 * sentinel资源名命名工具类
 * @author zhouzhixiang
 * @Date 2020-12-07
 */
public class SentinelResoucesNamer {

    private static final String SPLIT_TAG = "-";
    private static final String SENTINEL = "-sentinel";

    /**
     * Sentinel资源名获取（统一格式）
     * @param invokeClass       被调用服务类的Class
     * @param invokeMethodName  被调用服务方法名称
     * @param <T>
     * @return                  返回统一格式资源名
     */
    public static <T> String resourceName(Class<T> invokeClass, String invokeMethodName) {
        AssertUtil.notNull(invokeClass, "SentinelResoucesNamer resourceName Param invokeClass cannot be null");
        AssertUtil.assertNotBlank(invokeMethodName, "SentinelResoucesNamer resourceName Param invokeMethodName cannot be empty");
        String className = invokeClass.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append(className).append(SPLIT_TAG).append(invokeMethodName).append(SENTINEL);
        return sb.toString();
    }

}
