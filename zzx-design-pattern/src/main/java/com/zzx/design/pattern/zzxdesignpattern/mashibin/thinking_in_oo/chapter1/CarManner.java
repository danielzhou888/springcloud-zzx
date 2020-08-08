package com.zzx.design.pattern.zzxdesignpattern.mashibin.thinking_in_oo.chapter1;

/**
 * @author zhouzhixiang
 * @Date 2020-04-20
 */
public class CarManner implements VehicleManner {
    @Override
    public void preGo() {
        System.out.println(" start to 检查油量");
        System.out.println(" start to 加油");
        System.out.println(" start to 系安全带");
        System.out.println(" start to 挂档");
    }

    @Override
    public void go(Address address) {
        this.preGo();
        System.out.println("a common car start to go to the " + address.getDestination() + " destination");
    }
}
