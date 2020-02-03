//package com.zzx.design.pattern.zzxdesignpattern.functional_interface.chapter1;
//
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author zhouzhixiang
// * @company 叮当快药科技集团有限公司
// * @Date 2019-10-31
// */
//public class BaseController {
//
//    public void execute(RequestExecutor executor) {
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//        try {
//            Object result = executor.doExecutor();
//            writeAjaxResponse(result, msg);
//        } catch (BusinessExcetpion businessExcetpion) {
//            writeAjaxResponse(result, msg);
//            businessExcetpion.printStackTrace();
//        }
//
//    }
//}
