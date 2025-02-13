package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean;

import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.enums.CouponType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponSelectionDTO {
    
    @NotNull(message = "优惠券ID不能为空")
    private Long couponId;
    
    @NotNull(message = "优惠券金额不能为空")
    @Min(value = 0, message = "优惠券金额必须大于等于0")
    private BigDecimal amount;
    
    @NotNull(message = "优惠券类型不能为空")
    private CouponType couponType;
    
    private String description;
}