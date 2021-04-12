package com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.order;

import com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.req.YunCartOrderParam;
import com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.resp.ServiceResponse;

/**
 * 云购物车订单
 * @author zhouzhixiang
 * @Date 2020-12-04
 */
public class YunCartOrder extends IBaseOrder<YunCartOrderParam, ServiceResponse> {

    @Override
    public ServiceResponse create(YunCartOrderParam yunCartOrderParam)  {
        return null;
    }

    @Override
    public ServiceResponse update(YunCartOrderParam yunCartOrderParam) {
        return null;
    }

    @Override
    public void delete(Long orderId, Long userId) {

    }

    @Override
    public ServiceResponse execute(YunCartOrderParam yunCartOrderParam) {
        return null;
    }
}
