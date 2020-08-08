package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.anemia.controller;

import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.anemia.service.VirtualWalletService;

import java.math.BigDecimal;

/**
 * 基于贫血模型的传统开发模式
 * @author zhouzhixiang
 * @Date 2020-05-15
 */
public class VirtualWalletController {

    // ..
    private VirtualWalletService walletService;

    // 查询余额
    public BigDecimal getBalance(Long walletId) {
        return this.walletService.getBalance(walletId);
    }

    // 出账
    public void debit(Long walletId, BigDecimal amount) {
        this.walletService.debit(walletId, amount);
    }

    // 入账
    public void credit(Long walletId, BigDecimal amount) {
        this.walletService.credit(walletId, amount);
    }

    // 转账
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        this.walletService.transfer(fromWalletId, toWalletId, amount);
    }
}
