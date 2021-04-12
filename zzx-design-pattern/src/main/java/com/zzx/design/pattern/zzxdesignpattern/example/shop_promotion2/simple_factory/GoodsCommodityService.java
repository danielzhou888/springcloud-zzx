package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory;

import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory.bis.DeliverReq;
import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory.bis.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
public class GoodsCommodityService implements ICommodity {

    @Autowired
    private GoodsService goodsService;

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        DeliverReq req = DeliverReq.builder()
                .orderId(bizId)
                .sku(commodityId)
                .userName("花花")
                .userPhone("18949239399")
                .consigneeUserName(extMap.get("consigneeUserName"))
                .consigneeUserPhone(extMap.get("consigneeUserPhone"))
                .consigneeUserAddress(extMap.get("consigneeUserAddress"))
                .build();
        boolean b = goodsService.deliverGoods(req);
        if (!b)
            throw new RuntimeException("实物商品发放失败");
    }
}
