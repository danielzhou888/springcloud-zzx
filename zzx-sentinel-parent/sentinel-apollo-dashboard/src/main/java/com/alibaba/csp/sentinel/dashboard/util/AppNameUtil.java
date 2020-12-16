package com.alibaba.csp.sentinel.dashboard.util;

import com.alibaba.csp.sentinel.util.AssertUtil;

/**
 * AppName重命名工具类
 * @author zhouzhixiang
 * @Date 2020-12-08
 */
public class AppNameUtil {

    private static final String SENTINEL_SUFFIX = "-sen";

    public static String getCurrentAppNameSpace(String appName) {
        AssertUtil.assertNotBlank(appName, "appName cannot be null");
        return appName.concat(SENTINEL_SUFFIX);
    }
}
