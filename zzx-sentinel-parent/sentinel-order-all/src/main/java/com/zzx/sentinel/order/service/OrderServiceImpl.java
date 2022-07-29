package com.zzx.sentinel.order.service;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zzx.sentinel.distribute.enums.ResponseEnum;
import com.zzx.sentinel.distribute.response.ServiceResponse;
import com.zzx.sentinel.order.api.OrderApi;
import com.zzx.sentinel.order.api.wrapper.VoucherWrapApi;
import com.zzx.sentinel.order.mq.MQClient;
import com.zzx.sentinel.order.resp.OrderResult;
import com.zzx.sentinel.order.fallback.VoucherApiFallback;
import com.zzx.sentinel.order.utils.OrderUtils;
import com.zzx.sentinel.voucher.api.VoucherApi;
import com.zzx.sentinel.voucher.po.Promotion;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@DubboService(interfaceClass = OrderApi.class, dynamic = true)
@Slf4j
public class OrderServiceImpl implements OrderApi {

	@Autowired
    private VoucherWrapApi voucherApi;

	@Autowired
    private VoucherApi voucherOriginApi;

	private static ThreadPoolExecutor createOrderPool = new ThreadPoolExecutor(10, 20, 6, TimeUnit.SECONDS, new LinkedBlockingDeque<>(40), new ThreadPoolExecutor.CallerRunsPolicy());


    @Override
    public ServiceResponse createOrderAsync(Long userId) throws Exception {
        String orderCode = OrderUtils.getOrderCode(userId);
        ServiceResponse<OrderResult> response = new ServiceResponse<>();

        // 查询用户会员折扣
        CompletableFuture<Double> userRankDisCountFuture = null;
        try {
            AsyncEntry userRankDiscountEntry = SphU.asyncEntry("orderAll-voucherApi.queryUserRankDiscount-sentinel");
            userRankDisCountFuture = CompletableFuture.supplyAsync(() -> {
                Double userRankDiscount = 1d;
                try {
                    userRankDiscount = this.voucherApi.queryUserRankDiscount(userId);
                } catch (Exception e) {
                    log.error("wdcApi.queryUserRankDiscountByUserId exception {}", e);
                } finally {
                    userRankDiscountEntry.exit();
                }
                return userRankDiscount;
            }, createOrderPool);
        } catch (BlockException e) {
            // 本地调用优惠券查询用户会员折扣降级
            // 执行本地降级逻辑
            log.error("voucherApi.queryUserRankDiscount blockException {}", e);
            Double userRankDiscount = VoucherApiFallback.getUserRankDiscount(userId);
            userRankDisCountFuture.complete(userRankDiscount);
        }

        // 校验购物车商品
        CompletableFuture<Boolean> cartCheckFuture = null;
        try {
            AsyncEntry cartCheckEntry = SphU.asyncEntry("orderAll-voucherApi.cartCheck-sentinel");
            // 校验购物车商品促销情况。。。。。
            cartCheckFuture = CompletableFuture.supplyAsync(() -> {
                // ....校验
                boolean cartCheck;
                try {
                    cartCheck = this.voucherApi.cartCheck();
                } catch (Exception e) {
                    log.error("wdcApi.cartCheck exception {}", e);
                    cartCheck = false;
                } finally {
                    cartCheckEntry.exit();
                }
                return cartCheck;
            }, createOrderPool);

        } catch (BlockException e) {
            // 降级or限流 直接返回
            log.error("voucherApi.cartCheck blockException {}", e);
            // 发送MQ/Redis
            // 返回用户订单处理中，请稍后
            MQClient.sendMsg();
            response = new ServiceResponse<>(ResponseEnum.ORDER_PROCESSING.getCode(), ResponseEnum.ORDER_PROCESSING.getName());
            return response;
        }

        // 执行促销
        OrderResult result = new OrderResult();
        try {
            AsyncEntry executeResEntry = SphU.asyncEntry("orderAll-voucherApi.executePromotion-sentinel");
            CompletableFuture<Boolean> executeRes = userRankDisCountFuture.thenCombine(cartCheckFuture, (userRankDiscount, checkResult) -> {
                boolean executeResult = true;
                if (checkResult == true) {
                    try {
                        executeResult = this.voucherApi.executePromotion(userId, orderCode);
                    } catch (Exception e) {
                        log.error("wdcApi.executePromotion exception {}", e);
                        executeResult = false;
                    } finally {
                        executeResEntry.exit();
                    }
                } else {
                    executeResult = false;
                }
                return executeResult;
            });
            executeRes.join();
            result.setOrderId(orderCode);
            result.setExecuteResult(executeRes.get());
            response.setData(result);
        } catch (BlockException e) {
            log.error("voucherApi.executePromotion blockException {}", e);
            // 执行促销失败降级处理
            // 不调用优惠券服务，不走优惠
            boolean executeResult = VoucherApiFallback.executePromotionLocal(userId, orderCode);
            result.setOrderId(orderCode);
            result.setExecuteResult(executeResult);
        } catch (Exception e) {
            log.error("wdcApi.executePromotion exception {}", e);
            response = new ServiceResponse<>(ResponseEnum.EXECUTE_PROMOTION_EXCEPTION.getCode(), ResponseEnum.EXECUTE_PROMOTION_EXCEPTION.getName());
        }
        return response;
    }

    @Override
    public ServiceResponse createOrderSync(Long userId) throws Exception {
        String orderCode = OrderUtils.getOrderCode(userId);
        ServiceResponse response = new ServiceResponse();
        // 1-查询用户会员折扣
        ServiceResponse userRankDiscountResp = this.voucherApi.queryUserRankDiscountSentinel(userId);
        Double userRankDiscount = userRankDiscountResp.isSucces() ? (Double) userRankDiscountResp.getData() : 1d;

        // 2-校验购物车商品促销情况
        ServiceResponse cartCheckResp = this.voucherApi.cartCheckSentinel();
        if (cartCheckResp.isLocalDownGrade() || cartCheckResp.isDownGrade()) {
            // 如果本地执行优惠券/优惠券降级了
            // 发MQ/redis
            MQClient.sendMsg();
            // .....
            // 返回用户 -》 订单处理中，请稍后
            response = new ServiceResponse<>(ResponseEnum.ORDER_PROCESSING.getCode(), ResponseEnum.ORDER_PROCESSING.getName());
            return response;
        } else if (cartCheckResp.isSucces() && (boolean)cartCheckResp.getData()) {
            // 校验购物车商品促销成功了
            // 3-执行促销
            ServiceResponse executePromotionResp = this.voucherApi.executePromotionSentinel(userId, orderCode);
            if (executePromotionResp.isLocalDownGrade() || executePromotionResp.isDownGrade()) {
                // 如果本地调用优惠券/优惠券降级了
                // 发MQ/Redis
                MQClient.sendMsg();
                // 返回用户 -》 订单处理中，请稍后
                response = new ServiceResponse<>(ResponseEnum.ORDER_PROCESSING.getCode(), ResponseEnum.ORDER_PROCESSING.getName());
                return response;
            } else {
                if (executePromotionResp.isSucces()) {
                    // 取出promotion，处理 返回用户
                    Promotion promotion = (Promotion) executePromotionResp.getData();
                    if (promotion != null) {
                        // 处理 返回用户
                        // ......
                        response.setData(promotion);
                    }
                } else {
                    // 异常了，提示用户错误信息
                    return executePromotionResp;
                }
            }
        } else {
            // 校验购物车商品促销异常了
            // 返回提示用户异常信息
            response = cartCheckResp;
        }
        return response;
    }

    @Override
    public ServiceResponse globalBlockMethod() throws Exception {
        log.info("orderApi globalBlockMethod start...");
        return voucherApi.globalBlockMethod();
    }

    @Override
    public ServiceResponse testDefaultMachine() {
        return voucherApi.testDefaultMachine();
    }

    @Override
    public ServiceResponse testGlobalFallbackHandler() {
        List list = voucherOriginApi.testGlobalFallbackHandler(1,"zzx");
        ServiceResponse response = new ServiceResponse();
        response = voucherOriginApi.testGlobalFallbackReturnResponse(1, "zzx");
        if (CollectionUtils.isEmpty(list)) {
            log.info("降级了");
        }
        log.info("testGlobalFallbackHandler success");
        response.setData(list);
        return response;
    }
}
