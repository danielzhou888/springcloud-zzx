package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory;

import java.util.Map;

/**
 * 发奖接口
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
public interface ICommodity {

    void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception;
}
