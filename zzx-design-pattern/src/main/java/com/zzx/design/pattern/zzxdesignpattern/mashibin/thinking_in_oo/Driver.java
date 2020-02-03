package com.zzx.design.pattern.zzxdesignpattern.mashibin.thinking_in_oo;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-04
 */
public  class Driver {

    public void go(Vehicle vehicle, Address address) {
        vehicle.vehicleManner.go(address);
    }
}
