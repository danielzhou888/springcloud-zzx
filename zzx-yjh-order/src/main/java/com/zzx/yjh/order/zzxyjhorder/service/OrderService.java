package com.zzx.yjh.order.zzxyjhorder.service;


/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:12
 **/
public interface OrderService {

    String getOrderByOrderid(String orderId);

    String getUserByUserId(String userId);
}
