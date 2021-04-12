package com.zzx.sentinel.orderweb.fallback;

import com.zzx.sentinel.distribute.enums.ResponseEnum;
import com.zzx.sentinel.distribute.response.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 订单web限流降级处理
 * @author zhouzhixiang
 * @Date 2020-12-06
 */
@Slf4j
public class OrderControllerSentinell {

    /**
     * 异步创建订单链路限流降级逻辑
     * @param userId
     * @return
     */
    public static ServiceResponse createOrderAsyncSentinel(@RequestParam("userId") Long userId) {
        log.info("OrderControllerSentinell.createOrderAsyncSentinel 执行限流降级逻辑");
        return new ServiceResponse(ResponseEnum.CURRENT_VISITOR_LIMIT.getCode(), ResponseEnum.CURRENT_VISITOR_LIMIT.getName());
    }

    /**
     * 同步创建订单链路限流降级逻辑
     * @param userId
     * @return
     */
    public static ServiceResponse createOrderSyncSentinel(@RequestParam("userId") Long userId) {
        log.info("OrderControllerSentinell.createOrderSyncSentinel 执行限流降级逻辑");
        return new ServiceResponse(ResponseEnum.CURRENT_VISITOR_LIMIT.getCode(), ResponseEnum.CURRENT_VISITOR_LIMIT.getName());
    }

    /**
     * 自定义注解测试降级/限流
     * @return
     * @throws Exception
     */
    public static ServiceResponse testSentinelAnnotationSentinel() throws Exception {
        log.info("OrderControllerSentinell.testSentinelAnnotationSentinel 执行限流降级逻辑");
        return new ServiceResponse(ResponseEnum.CURRENT_VISITOR_LIMIT.getCode(), ResponseEnum.CURRENT_VISITOR_LIMIT.getName());
    }
}
