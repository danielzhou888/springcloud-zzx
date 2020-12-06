//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zzx.sentinel.voucher.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;

public class JSONUtil {
    static Logger logger = Logger.getLogger(JSONUtil.class);

    public JSONUtil() {
    }

    public Object json2Object(String jsonStr, String cls) {
        try {
            return this.json2Object(jsonStr, Class.forName(cls));
        } catch (ClassNotFoundException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public Object json2Object(String jsonStr, Class<?> cls) {
        ObjectMapper om = new ObjectMapper();
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            return om.readValue(jsonStr, cls);
        } catch (JsonParseException var5) {
            logger.error(var5);
            var5.printStackTrace();
        } catch (JsonMappingException var6) {
            logger.error(var6);
            var6.printStackTrace();
        } catch (IOException var7) {
            logger.error(var7);
            var7.printStackTrace();
        }

        return null;
    }

    public <T> T json2Object(String jsonStr, TypeReference<T> ref) {
        ObjectMapper om = new ObjectMapper();
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            return om.readValue(jsonStr, ref);
        } catch (JsonParseException var5) {
            logger.error(var5);
            var5.printStackTrace();
        } catch (JsonMappingException var6) {
            logger.error(var6);
            var6.printStackTrace();
        } catch (IOException var7) {
            logger.error(var7);
            var7.printStackTrace();
        }

        return null;
    }

    public String Object2JSONStr(Object obj) {
        ObjectMapper om = new ObjectMapper();

        try {
            return om.writeValueAsString(obj);
        } catch (JsonGenerationException var4) {
            logger.error(var4);
            var4.printStackTrace();
        } catch (JsonMappingException var5) {
            logger.error(var5);
            var5.printStackTrace();
        } catch (IOException var6) {
            logger.error(var6);
            var6.printStackTrace();
        }

        return null;
    }

    public static String object2JsonAsString(Object obj) {
        ObjectMapper om = new ObjectMapper();

        try {
            return om.writeValueAsString(obj);
        } catch (Exception var3) {
            logger.error(var3);
            return null;
        }
    }

}
