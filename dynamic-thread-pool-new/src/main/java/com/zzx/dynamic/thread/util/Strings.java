package com.zzx.dynamic.thread.util;

/**
 * @author zhouzhixiang
 * @Date 2021-04-09
 */
public abstract class Strings {

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static String blankDefault(String value, String defaultValue) {
        return isBlank(value) ? defaultValue : value;
    }

    public static int blankDefaultInt(String value, int defaultValue) {
        return isBlank(value) ? defaultValue : Integer.parseInt(value);
    }

    public static long blankDefaultLong(String value, long defaultValue) {
        return isBlank(value) ? defaultValue : Long.parseLong(value);
    }
}
