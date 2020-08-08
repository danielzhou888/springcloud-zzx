package com.zzx.design.pattern.zzxdesignpattern.virtual_wallet.anemia.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 虚拟钱包Bo
 * @author zhouzhixiang
 * @Date 2020-05-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualWalletBo {

    private Long id;

    private Long createTime;

    private BigDecimal balance;

}
