package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.repository;

import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.entity.VirtualWalletTransactionEntity;

/**
 * 可处理分布式事业的Repository
 * @author zhouzhixiang
 * @Date 2020-05-15
 */
public class VirtualWalletTransactionRepository {


    public Long saveTransaction(VirtualWalletTransactionEntity virtualWalletTransactionEntity) {
        return null;
    }

    public void updateStatus(Long transcationId, Integer executed) {
    }
}
