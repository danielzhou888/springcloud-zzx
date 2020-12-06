package com.zzx.sentinel.voucher.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zzx.sentinel.distribute.response.ServiceResponse;
import com.zzx.sentinel.voucher.api.VoucherApi;
import com.zzx.sentinel.voucher.po.Promotion;
import com.zzx.sentinel.voucher.sentinel.VoucherApiSentinel;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@Component
@DubboService(interfaceClass = VoucherApi.class, dynamic = true)
@Slf4j
public class VoucherServiceImpl implements VoucherApi {

    @Override
    public Double queryUserRankDiscount(Long userId) throws Exception {
        Thread.sleep(2000);
        return 0.8;
    }

    @Override
    public boolean executePromotion(Long userId, String orderId) throws Exception {
        Thread.sleep(2000);
        return true;
    }

    @Override
    public boolean cartCheck() throws Exception {
        Thread.sleep(2000);
        return true;
    }


    @SentinelResource(value = "voucherAll-VoucherApi-cartCheckReturnResp-sentinel", fallbackClass = VoucherApiSentinel.class, fallback = "cartCheckReturnRespSentinel")
    @Override
    public ServiceResponse cartCheckReturnResp() throws Exception {
        ServiceResponse response = new ServiceResponse();
        try {
            // ......
            Thread.sleep(2000);
            response.setData(true);
        } catch (Exception e) {
            log.error("VoucherServiceImpl.cartCheckReturnResp exception {}", e);
            response.setData(false);
        }
        return response;
    }

    @SentinelResource(value = "voucherAll-VoucherApi-executePromotionReturnResp-sentinel", fallbackClass = VoucherApiSentinel.class, fallback = "executePromotionReturnRespSentinel")
    @Override
    public ServiceResponse executePromotionReturnResp(Long userId, String orderCode) throws Exception {
        ServiceResponse response = new ServiceResponse();
        try {
            Thread.sleep(2000);
            // 执行促销
            response.setData(new Promotion());
        } catch (Exception e) {
            log.error("VoucherServiceImpl.executePromotionReturnResp exception {}", e);
            response.setSucces(false);
            // 或者返回枚举 扔给订单处理
        }
        return response;
    }

    @SentinelResource(value = "voucherAll-VoucherApi-queryUserRankDiscountSentinel-sentinel", fallbackClass = VoucherApiSentinel.class, fallback = "queryUserRankDiscountSentinelSentinel")
    @Override
    public ServiceResponse queryUserRankDiscountSentinel(Long userId) throws Exception {
        ServiceResponse response = new ServiceResponse();
        try {
            Thread.sleep(2000);
            // 执行促销
            response.setData(0.8d);
        } catch (Exception e) {
            log.error("VoucherServiceImpl.queryUserRankDiscountSentinel exception {}", e);
            response.setSucces(false);
            // 或者返回枚举 扔给订单处理
        }
        return response;
    }
}
