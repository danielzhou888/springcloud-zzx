package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.anemia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 虚拟钱包实体
 * @author zhouzhixiang
 * @Date 2020-05-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualWalletEntity {

    private Long id;

    private Long createTime;

    private BigDecimal balance;

}
