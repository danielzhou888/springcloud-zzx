//package com.zzx.sentinel.orderweb.config;
//
//import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
//import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Component      // 交由spring管理，否则拿不到RequestOriginParser实现类，授权规则不生效
//public class RequestOriginParserDefinition implements RequestOriginParser {
//    // 获取调用方标识信息并返回
//    @Override
//    public String parseOrigin(HttpServletRequest request) {
//        String serviceName = request.getParameter("serviceName");
//        //StringBuffer url = request.getRequestURL();
//        //if (url.toString().endsWith("favicon.ico")) {
//        //    // 浏览器会向后台请求favicon.ico图标
//        //    return serviceName;
//        //}
//
//        if (StringUtils.isEmpty(serviceName)) {
//            throw new IllegalArgumentException("serviceName must not be null");
//        }
//
//        return serviceName;
//    }
//}