package com.zzx.shardingsphere.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * @author zhouzhixiang
 * @Date 2020-03-25
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    public static boolean isBooleanValue(final String value) {
        return Boolean.TRUE.toString().equalsIgnoreCase(value) || Boolean.FALSE.toString().equalsIgnoreCase(value);
    }

    public static boolean isIntValue(final String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (final NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isLongValue(final String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (final NumberFormatException ex) {
            return false;
        }
    }
}
