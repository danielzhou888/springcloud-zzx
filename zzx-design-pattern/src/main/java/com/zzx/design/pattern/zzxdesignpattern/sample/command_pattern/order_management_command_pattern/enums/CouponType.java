package com.zzx.design.pattern.zzxdesignpattern.sample.command_pattern.order_management_command_pattern.enums;

public enum CouponType {
    CASH("现金券"),
    DISCOUNT("折扣券"),
    FULL_REDUCTION("满减券");
    
    private final String description;
    
    CouponType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}