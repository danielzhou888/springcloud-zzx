package com.zzx.sentinel.client.util;

/**
 * sentinel资源名命名工具类
 * @author zhouzhixiang
 * @Date 2020-12-07
 */
public class SentinelResoucesNamer {

    private static final String SPLIT_TAG = "-";
    private static final String SENTINEL = "-sentinel";
    private static final String LEFT_BRACKETS = "(";
    private static final String RIGHT_BRACKETS = ")";
    private static final String COMMA = ",";

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

    /**
     * Sentinel资源名获取（统一格式）
     * @param invokeClass       被调用服务类的Class
     * @param invokeMethodName  被调用服务方法名称
     * @param parameterTypes    参数类型
     * @param <T>
     * @return                  返回统一格式资源名
     */
    public static <T> String resourceNameAndMethod(Class<T> invokeClass, String invokeMethodName, Class<?>... parameterTypes) {
        AssertUtil.notNull(invokeClass, "SentinelResoucesNamer resourceName Param invokeClass cannot be null");
        AssertUtil.assertNotBlank(invokeMethodName, "SentinelResoucesNamer resourceName Param invokeMethodName cannot be empty");
        AssertUtil.notNull(parameterTypes, "SentinelResoucesNamer resourceName Param args cannot be null");
        String className = invokeClass.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append(className).append(SPLIT_TAG).append(invokeMethodName);
        if (parameterTypes.length > 0) {
            sb.append(LEFT_BRACKETS);
            for (int i = 0; i < parameterTypes.length; i++) {
                String paramterName = parameterTypes[i].getName();
                if (i < parameterTypes.length - 1) {
                    sb.append(paramterName).append(COMMA);
                } else {
                    sb.append(paramterName).append(RIGHT_BRACKETS);
                }
            }
        }
        sb.append(SENTINEL);
        return sb.toString();
    }

}
