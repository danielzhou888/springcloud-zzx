package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.full_model.bo;

import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.anemia.exception.InSufficientBalanceException;
import com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.anemia.exception.InvalidAmountException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 虚拟钱包Bo（基于充血模型）
 * @author zhouzhixiang
 * @Date 2020-05-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualWalletBo {

    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;
    private boolean isAllowedOverdraft = true;
    private BigDecimal overdraftAmount = BigDecimal.ZERO;
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public VirtualWalletBo(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public void freeze(BigDecimal amount) {

    }

    public void unfreeze(BigDecimal amount) {

    }

    public void increaseOverdraftAmount(BigDecimal amount) {

    }

    public void decreaseOverdraftAmount(BigDecimal amount) {

    }

    public void closeOverdraft() {

    }

    public void openOverdraft() {

    }

    public BigDecimal balance() {
        return this.balance;
    }

    public BigDecimal getAvailableBalance() {
        BigDecimal totalAvailableBalance = this.balance.subtract(this.frozenAmount);
        if (isAllowedOverdraft) {
            totalAvailableBalance.add(this.overdraftAmount);
        }
        return totalAvailableBalance;
    }

    public void debit(BigDecimal amount) {
        BigDecimal availableBalance = this.getAvailableBalance();
        if (availableBalance.compareTo(amount) < 0) {
            throw new InSufficientBalanceException();
        }
        this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException();
        }
        this.balance.add(amount);
    }

}
