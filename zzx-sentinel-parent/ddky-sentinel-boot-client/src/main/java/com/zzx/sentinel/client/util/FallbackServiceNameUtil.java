package com.zzx.sentinel.client.util;

/**
 * Fallback Service 命名工具类
 * @author zhouzhixiang
 * @Date 2020-12-08
 */
public class FallbackServiceNameUtil {

    private static final String SENTINEL_FALL_BACK_SERVICE_SUFFIX = "Fallback";

    public static String getFallbackServiceName(String interfaceName) {
        AssertUtil.assertNotBlank(interfaceName, "interfaceName cannot be empty");
        return interfaceName.concat(SENTINEL_FALL_BACK_SERVICE_SUFFIX);
    }
}
