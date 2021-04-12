package com.zzx.design.pattern.zzxdesignpattern.example.zhifubao_weixin_pay.mode;

/**
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
public class PayFingerPrintMode implements IPayMode {
    @Override
    public boolean security(String uId) {
        return true;
    }
}
