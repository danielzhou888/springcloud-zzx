package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean;

import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentFlow {
    private Long id;
    private String flowNo;
    private Long orderId;
    private String orderNo;
    private BigDecimal amount;
    private PaymentStatus status;
    private Date createTime;
}