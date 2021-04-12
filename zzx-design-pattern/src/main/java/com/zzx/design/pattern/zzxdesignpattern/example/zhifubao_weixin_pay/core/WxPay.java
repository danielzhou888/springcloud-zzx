package com.zzx.design.pattern.zzxdesignpattern.example.zhifubao_weixin_pay.core;

import com.zzx.design.pattern.zzxdesignpattern.example.zhifubao_weixin_pay.mode.IPayMode;

import java.math.BigDecimal;

/**
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
public class WxPay extends Pay {

    public WxPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public String transfer(String uid, String tradeId, BigDecimal amount) {
        boolean security = payMode.security(uid);
        if (!security)
            return "1";
        return "0";
    }
}
