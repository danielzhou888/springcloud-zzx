package com.zzx.sentinel.order.api.wrapper;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zzx.sentinel.distribute.response.ServiceResponse;
import com.zzx.sentinel.order.sentinel.VoucherApiSentinel;
import com.zzx.sentinel.voucher.api.VoucherApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 优惠券包装API
 * @author zhouzhixiang
 * @Date 2020-12-06
 */
@Component
@Slf4j
public class VoucherWrapApi {

    private static VoucherWrapApi voucherWrapApi;

    @DubboReference
    private VoucherApi voucherApi;

    @PostConstruct
    public void init() {
        voucherWrapApi = this;
        voucherWrapApi.voucherApi = this.voucherApi;
    }

    public Double queryUserRankDiscount(Long userId) throws Exception {
        return voucherApi.queryUserRankDiscount(userId);
    }

    public ServiceResponse queryUserRankDiscountSentinel(Long userId) {
        ServiceResponse response = new ServiceResponse();
        Entry entry = null;
        try {
            entry = SphU.entry("orderAll-voucherApi.queryUserRankDiscount-sentinel");
            response = voucherApi.queryUserRankDiscountSentinel(userId);
        } catch (BlockException e) {
            log.error("VoucherWrapApi.queryUserRankDiscount blockException {}", e);
            // 降级 不走会员优惠折扣
            response.setLocalDownGrade(true);
            Double userRankDiscount = VoucherApiSentinel.getUserRankDiscount(userId);
            response.setData(userRankDiscount);
        } catch (Exception ex) {
            log.error("VoucherWrapApi.queryUserRankDiscount exception {}", ex);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return response;
    }

    public boolean cartCheck() throws Exception {
         return voucherApi.cartCheck();
    }

    public ServiceResponse cartCheckSentinel() {
        ServiceResponse response = new ServiceResponse();
        Entry entry = null;
        try {
            entry = SphU.entry("orderAll-voucherApi.cartCheckReturnResp-sentinel");
            response = voucherApi.cartCheckReturnResp();
        } catch (BlockException e) {
            log.error("VoucherWrapApi.cartCheck blockException {}", e);
            response.setLocalDownGrade(true);
        } catch (Exception ex) {
            log.error("VoucherWrapApi.cartCheck exception {}", ex);
            Tracer.traceEntry(ex, entry);
            response.setData(false);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return response;
    }

    public boolean executePromotion(Long userId, String orderCode) throws Exception {
        return voucherApi.executePromotion(userId, orderCode);
    }

    public ServiceResponse executePromotionSentinel(Long userId, String orderCode) {
        ServiceResponse response = new ServiceResponse();
        Entry entry = null;
        try {
            entry = SphU.entry("orderAll-voucherApi.executePromotionReturnResp-sentinel");
            response = voucherApi.executePromotionReturnResp(userId, orderCode);
            if (response.isDownGrade()) {
                // 如果优惠券执行降级了，走本地执行促销逻辑
                boolean b = VoucherApiSentinel.executePromotionLocal(userId, orderCode);
                response.setData(b);
            }
        } catch (BlockException e) {
            log.error("VoucherWrapApi.executePromotion blockException {}", e);
            response.setLocalDownGrade(true);
        } catch (Exception ex) {
            log.error("VoucherWrapApi.executePromotion exception {}", ex);
            response.setSucces(false);
            Tracer.traceEntry(ex, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return response;
    }

    public ServiceResponse globalBlockMethod() throws Exception {
        log.info("voucherWrapApi.globalBlockMethod start...");
        return voucherApi.globalBlockMethod();
    }

    public ServiceResponse testDefaultMachine() {
        return voucherApi.testDefaultMachine();
    }
}
