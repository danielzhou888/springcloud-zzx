package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern;

import com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.bean.CouponSelectionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDTO {
    @NotNull
    private Long userId;
    
    @NotBlank
    private String skuId;
    
    @Min(1)
    private Integer quantity;
    
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String deliveryDate;
    
    @Valid
    private List<CouponSelectionDTO> coupons;
}