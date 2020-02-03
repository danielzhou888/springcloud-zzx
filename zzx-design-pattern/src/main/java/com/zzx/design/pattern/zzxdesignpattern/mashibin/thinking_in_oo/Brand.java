package com.zzx.design.pattern.zzxdesignpattern.mashibin.thinking_in_oo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 商品品牌
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-04
 */
@Data
@AllArgsConstructor
@Builder
public class Brand {
    private Integer id;
    private String name;
}
