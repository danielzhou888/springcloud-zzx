package com.zzx.design.pattern.zzxdesignpattern.decorator.product_price_strategy.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-08-11
 */
@Data
public class Product {

    private String sku;
    private String name;
    private BigDecimal price;
    // 支持促销类型
    private Map<PromotionType, SupportPromotions> supportPromotions;
}
