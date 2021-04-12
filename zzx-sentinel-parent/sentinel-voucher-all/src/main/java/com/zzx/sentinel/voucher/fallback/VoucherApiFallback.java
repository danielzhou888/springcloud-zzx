package com.zzx.sentinel.voucher.fallback;

import com.zzx.sentinel.client.fallback.proxy.FallbackProxy;
import com.zzx.sentinel.distribute.response.ServiceResponse;
import com.zzx.sentinel.voucher.api.VoucherApi;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 促销接口限流/降级处理类
 * @author zhouzhixiang
 * @Date 2020-12-06
 */
@Slf4j
public class VoucherApiFallback implements FallbackProxy<VoucherApi> {

    /**
     * 校验购物车商品促销情况降级逻辑
     * @return
     * @throws Exception
     */
    public static ServiceResponse cartCheckReturnRespSentinel() throws Exception {
        log.warn("VoucherApiSentinel.cartCheckReturnRespSentinel 执行降级逻辑");
        ServiceResponse response = new ServiceResponse();
        response.setData(false);
        response.setDownGrade(true);
        return response;
    }

    /**
     * 执行促销降级逻辑
     * @param userId
     * @param orderCode
     * @return
     * @throws Exception
     */
    public static ServiceResponse executePromotionReturnRespSentinel(Long userId, String orderCode) throws Exception {
        log.warn("VoucherApiSentinel.cartCheckReturnRespSentinel userId = {}, orderCode = {} 执行降级逻辑", userId, orderCode);
        ServiceResponse response = new ServiceResponse();
        response.setDownGrade(true);
        return response;
    }

    public static ServiceResponse testDefaultMachineSentinel() {
        log.info("voucherAll voucherApi testDefaultMachineSentinel 限流/降级了");
        return ServiceResponse.fail("限流/降级了");
    }

    public List testGlobalFallbackHandler(int type, String name) {
        List list = new ArrayList();
        list.add("voucher degrade .... ");
        return list;
    }
}
