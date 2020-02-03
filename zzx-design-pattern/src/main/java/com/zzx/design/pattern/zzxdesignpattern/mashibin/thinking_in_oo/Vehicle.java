package com.zzx.design.pattern.zzxdesignpattern.mashibin.thinking_in_oo;

import lombok.Data;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-04
 */
@Data
public abstract class Vehicle {
    protected Brand brand;
    protected VehicleManner vehicleManner;
    protected Vehicle (Brand brand, VehicleManner vehicleManner) {
        this.brand = brand;
    }
}
