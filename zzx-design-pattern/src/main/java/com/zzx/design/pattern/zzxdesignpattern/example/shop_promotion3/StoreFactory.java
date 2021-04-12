package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion3;

import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion3.impl.CouponCommodityService;
import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion3.impl.GoodsCommodityService;
import com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion3.impl.IQiYiCardCommodityService;

/**
 * @author zhouzhixiang
 * @Date 2020-11-30
 */
public class StoreFactory {

    public ICommodity getCommodityService(Integer type) {
        if (type == 1) {
            return new CouponCommodityService();
        } else if (type == 2) {
            return new GoodsCommodityService();
        } else if (type == 3) {
            return new IQiYiCardCommodityService();
        } else {
            throw new RuntimeException("不存在的商品服务类型");
        }


    }
}
