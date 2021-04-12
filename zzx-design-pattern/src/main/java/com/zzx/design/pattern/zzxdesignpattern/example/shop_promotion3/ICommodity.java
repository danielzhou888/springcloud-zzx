package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion3;

import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-11-30
 */
public interface ICommodity {

    void sendCommodity(String uId, String commodityId, String bizId, Map<String, String > extMap) throws Exception;
}
