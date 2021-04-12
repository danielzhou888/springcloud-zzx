package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.service;

import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.bo.VirtualWalletBo;
import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.consts.Status;
import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.entity.VirtualWalletEntity;
import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.entity.VirtualWalletTransactionEntity;
import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.exception.BusinessException;
import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.exception.InSufficientBalanceException;
import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.exception.NoSuchVirtualWalletException;
import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.exception.NoSufficientBalanceException;
import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.repository.VirtualWalletRepository;
import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.repository.VirtualWalletTransactionRepository;

import java.math.BigDecimal;

/**
 * 虚拟钱包Service  基于充血模型的DDD开发模式
 * @author zhouzhixiang
 * @Date 2020-05-15
 */
public class VirtualWalletService {

    // ...
    private VirtualWalletRepository virtualWalletRepository;

    // 可处理分布式事物的Repository
    private VirtualWalletTransactionRepository virtualWalletTransactionRepository;

    public BigDecimal getBalance(Long walletId) {
        return virtualWalletRepository.getBalance(walletId);
    }

    public VirtualWalletBo getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = this.virtualWalletRepository.getWalletEntity(walletId);
        if (walletEntity == null) return new VirtualWalletBo();
        VirtualWalletBo virtualWalletBo = convert(walletEntity);
        return virtualWalletBo;
    }

    private VirtualWalletBo convert(VirtualWalletEntity walletEntity) {
        VirtualWalletBo bo = new VirtualWalletBo();
        bo.setId(walletEntity.getId());
        bo.setBalance(walletEntity.getBalance());
        bo.setCreateTime(walletEntity.getCreateTime());
        return bo;
    }

    public void debit(Long walletId, BigDecimal amount) throws NoSuchVirtualWalletException, NoSufficientBalanceException {
        VirtualWalletEntity virtualWalletEntity = this.virtualWalletRepository.getWalletEntity(walletId);
        if (virtualWalletEntity == null) {
            throw new NoSuchVirtualWalletException();
        }
        BigDecimal balance = virtualWalletEntity.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new NoSufficientBalanceException();
        }
        VirtualWalletBo bo = convert(virtualWalletEntity);
        bo.debit(amount);
        virtualWalletRepository.updateBalance(walletId, balance.subtract(amount));
    }

    public void credit(Long walletId, BigDecimal amount) throws NoSuchVirtualWalletException {
        VirtualWalletEntity walletEntity = this.virtualWalletRepository.getWalletEntity(walletId);
        if (walletEntity == null) {
            throw new NoSuchVirtualWalletException();
        }
        BigDecimal balance = walletEntity.getBalance();
        this.virtualWalletRepository.updateBalance(walletId, balance.add(amount));
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        VirtualWalletTransactionEntity virtualWalletTransactionEntity = VirtualWalletTransactionEntity.builder()
                .createTime(System.currentTimeMillis())
                .fromWalletId(fromWalletId)
                .toWalletId(toWalletId)
                .amount(amount)
                .status(Status.TO_BE_EXECUTED)
                .build();
        Long transcationId = this.virtualWalletTransactionRepository.saveTransaction(virtualWalletTransactionEntity);

        try {
            debit(fromWalletId, amount);
            credit(toWalletId, amount);
        } catch (NoSuchVirtualWalletException e) {
            this.virtualWalletTransactionRepository.updateStatus(transcationId, Status.TO_BE_CLOSED);
            throw new NoSuchVirtualWalletException();
        } catch (NoSufficientBalanceException e) {
            this.virtualWalletTransactionRepository.updateStatus(transcationId, Status.TO_BE_FAILED);
            throw new NoSufficientBalanceException();
        } catch (InSufficientBalanceException e) {
            this.virtualWalletTransactionRepository.updateStatus(transcationId, Status.TO_BE_FAILED);
            throw new InSufficientBalanceException();
        } catch (Exception e) {
            this.virtualWalletTransactionRepository.updateStatus(transcationId, Status.TO_BE_FAILED);
            throw new BusinessException("转账异常");
        }
        this.virtualWalletTransactionRepository.updateStatus(transcationId, Status.EXECUTED);
    }
}
