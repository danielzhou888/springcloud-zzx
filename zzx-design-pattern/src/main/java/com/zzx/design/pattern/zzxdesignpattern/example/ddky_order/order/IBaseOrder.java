package com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.order;

import com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.enums.ClientPlatform;
import com.zzx.design.pattern.zzxdesignpattern.example.ddky_order.enums.OrderSource;

/**
 * 订单父类
 * @author zhouzhixiang
 * @Date 2020-12-04
 */
public abstract class IBaseOrder<Q, R> {

    // 客户端平台
    protected ClientPlatform clientPlatform;

    // 订单来源
    protected OrderSource orderSource;

    protected void init() {

    }

    abstract R create(Q q);

    abstract R update(Q q);

    abstract void delete(Long orderId, Long userId);

    abstract R execute(Q q);


}
