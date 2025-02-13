package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean;

import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.enums.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id;
    private String orderNo;
    private Long userId;
    private String skuId;
    private Integer quantity;
    private OrderStatus status;
    private Date createTime;
    private Date updateTime;
}