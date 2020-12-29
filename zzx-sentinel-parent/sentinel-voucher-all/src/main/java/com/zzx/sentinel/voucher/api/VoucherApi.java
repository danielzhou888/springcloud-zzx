package com.zzx.sentinel.voucher.api;


import com.zzx.sentinel.distribute.response.ServiceResponse;

import javax.xml.ws.Service;
import java.util.List;

public interface VoucherApi {

	Double queryUserRankDiscount(Long userId) throws Exception;

	boolean executePromotion(Long userId, String orderId) throws Exception;

	boolean cartCheck() throws Exception;

	ServiceResponse cartCheckReturnResp() throws Exception;

	ServiceResponse executePromotionReturnResp(Long userId, String orderCode) throws Exception;

	ServiceResponse queryUserRankDiscountSentinel(Long userId) throws Exception;

    ServiceResponse globalBlockMethod() throws Exception;

	ServiceResponse testDefaultMachine();

    ServiceResponse testUseSentinelResourceNamer();

	List testGlobalFallbackHandler(int type, String name);

    ServiceResponse testGlobalFallbackReturnResponse(int type, String name);
}
