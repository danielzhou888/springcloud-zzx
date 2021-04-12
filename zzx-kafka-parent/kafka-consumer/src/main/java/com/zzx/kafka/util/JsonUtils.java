package com.zzx.kafka.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class JsonUtils {
    /**
     * 日志输出类
     */
    private static Logger Log = LoggerFactory.getLogger(JsonUtils.class.getName());
    private static ObjectMapper mapper = new ObjectMapper();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static{
        mapper.setDateFormat(sdf);
    }

    /**
     *  对象转json
     * @param obj
     * @return
     */
    public static String object2string(Object obj)  {
        try {
            String json = mapper.writeValueAsString(obj);
            return  json;
        }catch (Exception e){
            Log.error("error : {}",e);
        }
        return  null;
    }

    /**
     *  自定日期格式对象转JSON
     * @param obj 对象
     * @param format 时间格式
     * @return
     */
    public static String object2string(Object obj,String format)  {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        ObjectMapper om = new ObjectMapper();
        try {
            om.setDateFormat(sdf);
            String json = om.writeValueAsString(obj);
            return  json;
        }catch (Exception e){
            Log.error("error : {}", e);
        }
        return  null;
    }

	 /**
     * JSON转成对象
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> T json2Object(String jsonString, Class<T> clazz) {
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return null;
    }
    
    /**
     *  对象转json
     * @param obj
     * @return
     */
    public static String object2stringWithUnderscores(Object obj) {
        try {
        	mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            String json = mapper.writeValueAsString(obj);
            return  json;
        }catch (Exception e){
            Log.error("error:{}", e);
        }
        return  null;
    }

    /**
     * 判断字符串是否json
     * @param json
     * @return
     */
    public static boolean isJson(String json) {
        try {
            JSONObject jsonStr= JSONObject.parseObject(json);
            return  true;
        } catch (Exception e) {
            return false;
        }
    }

}
