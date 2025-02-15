package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion3.impl;

import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion3.ICommodity;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 优惠券接口
 * @author zhouzhixiang
 * @Date 2020-11-30
 */
@Slf4j
public class CouponCommodityService implements ICommodity {
    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        log.info("发放优惠券");
    }
}
