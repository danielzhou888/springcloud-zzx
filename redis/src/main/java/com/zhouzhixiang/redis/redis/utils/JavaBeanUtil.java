package com.zhouzhixiang.redis.redis.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean处理工具类<br>
 * 1. Bean与Map互转
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-07-14 12:24
 **/
public class JavaBeanUtil {

    /**
     * Object entity转map
     * @param obj
     * @return
     */
    public static Map<String, Object> entityToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                map.put(declaredField.getName(), declaredField.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * map转实体
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T mapToEntity(Map<Object, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
                    continue;
                }
                field.setAccessible(true);
                String fieldTypeName = field.getType().getName();
                if (fieldTypeName.equalsIgnoreCase("java.util.Date")) {
                    String dateTimeStr = String.valueOf(map.get(field.getName()));
                    if (dateTimeStr.equalsIgnoreCase("null")) {
                        field.set(obj, null);
                    } else {
                        field.set(obj, new Date(Long.parseLong(dateTimeStr)));
                    }
                } else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            return obj;
        }
    }
}
