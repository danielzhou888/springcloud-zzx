package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.consts;

/**
 * 交易操作状态
 * @author zhouzhixiang
 * @Date 2020-05-16
 */
public class Status {

    public static final Integer TO_BE_CLOSED = 1; // 交易关闭
    public static final Integer TO_BE_FAILED = 2; // 交易失败
    public static final Integer TO_BE_EXECUTED = 3; // 交易进行中
    public static final Integer EXECUTED = 4; // 交易成功

}
