package com.zzx.sentinel.voucher.service;

import com.zzx.sentinel.client.annotation.SentinelAnnotation;
import com.zzx.sentinel.distribute.response.ServiceResponse;
import com.zzx.sentinel.voucher.api.VoucherApi;
import com.zzx.sentinel.voucher.po.Promotion;
import com.zzx.sentinel.voucher.fallback.VoucherApiFallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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


    @SentinelAnnotation(value = "voucherAll-VoucherApi-cartCheckReturnResp-sentinel", fallbackClass = VoucherApiFallback.class, fallback = "cartCheckReturnRespSentinel")
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

    @SentinelAnnotation(value = "voucherAll-VoucherApi-executePromotionReturnResp-sentinel", fallbackClass = VoucherApiFallback.class, fallback = "executePromotionReturnRespSentinel")
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

    @SentinelAnnotation(value = "voucherAll-VoucherApi-queryUserRankDiscountSentinel-sentinel", fallbackClass = VoucherApiFallback.class, fallback = "queryUserRankDiscountSentinelSentinel")
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

    @Override
    public ServiceResponse globalBlockMethod() throws Exception {
        log.info("voucherApi.globalBlockMethod start....");
        return new ServiceResponse();
    }

    @SentinelAnnotation(value = "voucherAll-VoucherApi-testDefaultMachine-sentinel", fallbackClass = VoucherApiFallback.class, fallback = "testDefaultMachineSentinel")
    @Override
    public ServiceResponse testDefaultMachine() {
        return new ServiceResponse();
    }

    @Override
    public ServiceResponse testUseSentinelResourceNamer() {
        return new ServiceResponse();
    }

    @Override
    public List testGlobalFallbackHandler(int type, String name) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        return list;
    }

    @Override
    public ServiceResponse testGlobalFallbackReturnResponse(int type, String name) {
        ServiceResponse response = new ServiceResponse();
        response.setMsg("我是voucher 成功");
        return response;
    }
}
