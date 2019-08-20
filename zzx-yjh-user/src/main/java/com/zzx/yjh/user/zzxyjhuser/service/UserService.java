package com.zzx.yjh.user.zzxyjhuser.service;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Daniel Zhou / zzx
 * @Date 2019-08-11 15:47
 **/
public interface UserService {

    String getUserInfoByUserId(String userId);

    String getOrderInfoByOrderId(String orderId);
}
