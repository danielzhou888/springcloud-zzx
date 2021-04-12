package com.zzx.design.pattern.zzxdesignpattern.example.shop_promotion2.simple_factory.bis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DeliverReq {

    private String userName;
    private String userPhone;
    private String sku;
    private String orderId;
    private String consigneeUserName;
    private String consigneeUserPhone;
    private String consigneeUserAddress;

}
