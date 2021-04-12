package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.repository;

import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.entity.VirtualWalletEntity;

import java.math.BigDecimal;

/**
 * 虚拟钱包Repository
 * @author zhouzhixiang
 * @Date 2020-05-15
 */
public class VirtualWalletRepository {


    public BigDecimal getBalance(Long walletId) {
        return null;
    }

    public void updateBalance(Long walletId, BigDecimal subtract) {
    }

    public VirtualWalletEntity getWalletEntity(Long walletId) {
        return new VirtualWalletEntity();
    }
}
