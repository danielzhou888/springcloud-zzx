package com.zzx.sentinel.order.api;

import com.zzx.sentinel.distribute.response.ServiceResponse;

public interface OrderApi {

	ServiceResponse createOrderAsync(Long userId) throws Exception;

	ServiceResponse createOrderSync(Long userId) throws Exception;
}
