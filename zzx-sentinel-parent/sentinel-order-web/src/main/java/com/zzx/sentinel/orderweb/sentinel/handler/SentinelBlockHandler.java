package com.zzx.sentinel.orderweb.sentinel.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzx.sentinel.distribute.enums.SentinelEnum;
import com.zzx.sentinel.distribute.response.ServiceResponse;
import com.zzx.sentinel.orderweb.utils.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 限流降级统一处理类
 * @author zhouzhixiang
 * @Date 2020-12-01
 */
@Configuration
public class SentinelBlockHandler implements UrlBlockHandler {

    private static final Logger logger = LoggerFactory.getLogger(SentinelBlockHandler.class);

    static {
        // 初始化限流降级全局处理器
        WebCallbackManager.setUrlBlockHandler(new SentinelBlockHandler());
    }

    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
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
        logger.info("全局流控处理：{}", JSONUtil.object2JsonAsString(response));
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        new ObjectMapper().writeValue(httpServletResponse.getWriter(), response);
    }
}
