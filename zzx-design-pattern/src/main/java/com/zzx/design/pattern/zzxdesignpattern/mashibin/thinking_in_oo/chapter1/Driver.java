package com.zzx.design.pattern.zzxdesignpattern.mashibin.thinking_in_oo.chapter1;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhouzhixiang
 * @Date 2020-04-20
 */
@Data
@AllArgsConstructor
public class Driver {

    private String name;
    public void go(Vehicle vehicle, Address address) {
        vehicle.getVehicleManner().go(address);
    }
}
