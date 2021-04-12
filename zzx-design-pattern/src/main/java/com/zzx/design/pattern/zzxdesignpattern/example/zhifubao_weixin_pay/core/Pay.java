package com.zzx.design.pattern.zzxdesignpattern.example.zhifubao_weixin_pay.core;

import com.zzx.design.pattern.zzxdesignpattern.example.zhifubao_weixin_pay.mode.IPayMode;

import java.math.BigDecimal;

/**
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
public abstract class Pay {

    protected IPayMode payMode;

    public Pay(final IPayMode payMode) {
        this.payMode = payMode;
    }

    public abstract String transfer(String uid, String tradeId, BigDecimal amount);
}
