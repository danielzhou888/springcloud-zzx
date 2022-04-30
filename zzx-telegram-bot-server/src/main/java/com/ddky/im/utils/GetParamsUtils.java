//package com.ddky.im.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URLDecoder;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @description: 参数提取工具类
// * @author: zhouzhixiang
// * @date: 2019-12-10
// * @company: 叮当快药科技集团有限公司
// **/
//public class GetParamsUtils {
//    private static final Logger log = LoggerFactory.getLogger(GetParamsUtils.class);
//
//    public static String jsonReq(HttpServletRequest request) {
//        BufferedReader br;
//        StringBuilder sb = null;
//        String reqBody = null;
//        try {
//            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//            String line = null;
//            sb = new StringBuilder();
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//            br.close();
//            if (sb.length() < 1) return "";
//            reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
//            reqBody = reqBody.substring(reqBody.indexOf("{"));
//            return reqBody;
//        } catch (IOException e) {
//            log.error("获取json参数错误！{}", e.getMessage());
//            return "";
//        }
//    }
//
//    public static <T> T getRequestVo(HttpServletRequest request, Class<T> clazz) {
//        String json = jsonReq(request);
//        T t = null;
//        try {
//            t = JSONObject.parseObject(json, clazz);
//        } catch (Exception e) {
//            log.error("getRequestVo error : {}", e);
//        } finally {
//            return t;
//        }
//    }
//
//    public static Map<String, String> getParamsMap(HttpServletRequest request) {
//        Map<String, String> paramsMap = new HashMap<>();
//        String json = jsonReq(request);
//        if (StringUtils.isNotBlank(json)) {
//            return JSONObject.parseObject(json, Map.class);
//        }
//        return paramsMap;
//    }
//
//    public static Map<String, String> getParamsMap() {
//        Map<String, String> paramsMap = new HashMap<>();
//        try {
//            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = sra.getRequest();
//            String json = jsonReq(request);
//            if (StringUtils.isNotBlank(json)) {
//                return JSONObject.parseObject(json, Map.class);
//            }
//        } catch (Exception e) {
//            log.error("json参数转换错误！{}", e.getMessage());
//        }
//        return paramsMap;
//    }
//
//}