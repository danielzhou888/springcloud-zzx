package com.zzx.sentinel.order.sentinel.handler;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.zzx.sentinel.distribute.enums.SentinelEnum;
import com.zzx.sentinel.distribute.response.ServiceResponse;
import com.zzx.sentinel.order.utils.JSONUtil;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * 限流降级统一处理类
 * @author zhouzhixiang
 * @Date 2020-12-01
 */
@Configuration
public class SentinelDubboBlockHandler {

    private static final Logger logger= LoggerFactory.getLogger(SentinelDubboBlockHandler.class);

    static {
        DubboFallbackRegistry.setProviderFallback(new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException e) {
                ServiceResponse response = doSentinelExceptionProcess(e);
                String invokerName = invoker.getInterface().getName();
                String methodName = invocation.getMethodName();
                logger.info("服务提供方全局流控处理 -> provider route {}.{}，result：{}", invokerName, methodName, JSONUtil.object2JsonAsString(response));
                return AsyncRpcResult.newDefaultAsyncResult(response, invocation);
            }
        });

        DubboFallbackRegistry.setConsumerFallback(new DubboFallback() {
            @Override
            public Result handle(Invoker<?> invoker, Invocation invocation, BlockException e) {
                ServiceResponse response = doSentinelExceptionProcess(e);
                String invokerName = invoker.getInterface().getName();
                String methodName = invocation.getMethodName();
                logger.info("服务消费方全局流控处理 -> consumer route {}.{}，result：{}", invokerName, methodName, JSONUtil.object2JsonAsString(response));
                return AsyncRpcResult.newDefaultAsyncResult(response, invocation);
            }
        });
    }

    private static ServiceResponse doSentinelExceptionProcess(BlockException e) {
        ServiceResponse response = null;
        if (e instanceof FlowException) {
            // 限流异常
            response = new ServiceResponse(SentinelEnum.FLOW_EXCEPTION.getCode(), SentinelEnum.FLOW_EXCEPTION.getName());
        } else if (e instanceof DegradeException) {
            // 降级异常
            response = new ServiceResponse(SentinelEnum.DEGRADE_EXCEPTION.getCode(), SentinelEnum.DEGRADE_EXCEPTION.getName());
        } else if (e instanceof ParamFlowException) {
            // 热点参数降级异常
            response = new ServiceResponse(SentinelEnum.PARAM_FLOW_EXCEPTION.getCode(), SentinelEnum.PARAM_FLOW_EXCEPTION.getName());
        } else if (e instanceof SystemBlockException) {
            // 系统规则异常
            response = new ServiceResponse(SentinelEnum.SYSTEM_BLOCK_EXCEPTION.getCode(), SentinelEnum.SYSTEM_BLOCK_EXCEPTION.getName());
        } else if (e instanceof AuthorityException) {
            // 授权规则异常
            response = new ServiceResponse(SentinelEnum.SYSTEM_BLOCK_EXCEPTION.getCode(), SentinelEnum.SYSTEM_BLOCK_EXCEPTION.getName());
        }
        return response;
    }
}
