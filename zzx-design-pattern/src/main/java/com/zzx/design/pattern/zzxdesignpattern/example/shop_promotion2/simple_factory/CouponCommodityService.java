package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory.bis.CouponResult;
import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory.bis.CouponService;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 优惠券接口
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
@Slf4j
public class CouponCommodityService implements ICommodity {


    @Autowired
    private CouponService couponService;

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        CouponResult couponResult = couponService.sendCoupon(uId, commodityId, bizId);
        log.info("请求参数【优惠券】 =》 uid：{} commodityId：{} bizId：{} extMap：{}", uId, commodityId, bizId, JSONObject.toJSON(extMap));
    }
}
