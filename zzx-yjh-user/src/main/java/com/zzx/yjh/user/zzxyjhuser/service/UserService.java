package com.zzx.yjh.user.zzxyjhuser.service;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:47
 **/
public interface UserService {

    String getUserInfoByUserId(String userId);

    String getOrderInfoByOrderId(String orderId);
}
