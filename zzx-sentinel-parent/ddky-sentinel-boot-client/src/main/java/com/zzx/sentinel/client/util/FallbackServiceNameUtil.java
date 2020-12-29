package com.zzx.sentinel.client.util;

/**
 * Fallback Service 命名工具类
 * @author zhouzhixiang
 * @Date 2020-12-08
 */
public class FallbackServiceNameUtil {

    private static final String SENTINEL_FALL_BACK_SERVICE_SUFFIX = "Fallback";
    private static final String SPLIT_FLAG = ":";
    private static final String LEFT_BRACKETS = "(";
    private static final String RIGHT_BRACKETS = ")";
    private static final String COMMA = ",";

    public static String getFallbackServiceName(String interfaceName) {
        AssertUtil.assertNotBlank(interfaceName, "interfaceName cannot be empty");
        return interfaceName.concat(SENTINEL_FALL_BACK_SERVICE_SUFFIX);
    }

    public static <T> String getFallbackBeanMethodkey(Class<T> invokeClass, String methodName, Class<?>... parameterTypes) {
        String className = invokeClass.getName();
        StringBuilder sb = new StringBuilder();
        sb.append(className).append(SPLIT_FLAG).append(methodName);
        sb.append(LEFT_BRACKETS);
        if (parameterTypes.length >  0) {
            for (int i = 0; i < parameterTypes.length; i++) {
                String paramterName = parameterTypes[i].getName();
                if (i < parameterTypes.length - 1) {
                    sb.append(paramterName).append(COMMA);
                } else {
                    sb.append(paramterName).append(RIGHT_BRACKETS);
                }
            }
        }
        sb.append(RIGHT_BRACKETS);
        return sb.toString();
    }
}
