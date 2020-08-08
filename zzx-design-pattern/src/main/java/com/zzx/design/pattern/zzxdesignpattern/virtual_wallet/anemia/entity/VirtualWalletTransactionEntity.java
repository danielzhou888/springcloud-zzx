package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.anemia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhouzhixiang
 * @Date 2020-05-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualWalletTransactionEntity implements Serializable {
    private static final long serialVersionUID = -7115156414108115443L;

    private Long id;

    private Long createTime;

    private BigDecimal amount;

    private Long fromWalletId;

    private Long toWalletId;

    private int status;

}
