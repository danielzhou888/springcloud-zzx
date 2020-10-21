//package com.zzx.sentinel.orderweb.handler;
//
//import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
//import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
//import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
//import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
//import com.zzx.sentinel.distribute.enums.ResponseEnum;
//import com.zzx.sentinel.distribute.response.ServiceResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.lang.reflect.UndeclaredThrowableException;
//
//@Slf4j
//@RestControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class SentinelExceptionHandler{
//    @ExceptionHandler(value = UndeclaredThrowableException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public static ServiceResponse handleException(UndeclaredThrowableException ex) {
//        log.error(ex.getMessage(),ex);
//        Throwable undeclaredThrowable = ex.getUndeclaredThrowable();
//        if(undeclaredThrowable instanceof FlowException){
//            return ServiceResponse.fail("限流啦！");
//        }else if(undeclaredThrowable instanceof DegradeException){
//            return ServiceResponse.fail("熔断啦！");
//        }else if(undeclaredThrowable instanceof ParamFlowException){
//            return ServiceResponse.fail("热点限流啦！");
//        }else if(undeclaredThrowable instanceof SystemBlockException){
//            return ServiceResponse.fail("系统限流啦！");
//        }else if(undeclaredThrowable instanceof AuthorityException){
//            return ServiceResponse.fail(ResponseEnum.UNAUTHORIZED.getCode(),"授权限制了！");
//        }
//        return ServiceResponse.fail("Sentinel系统保护！");
//    }
//}