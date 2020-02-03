package com.zzx.design.pattern.zzxdesignpattern.mashibin.thinking_in_oo;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-11-04
 */
public class Main {
    public static void main(String[] args) {
        Car car = new Car(new Brand(1, "宝马"), new CarManner());

        Address address = new Address("北京");
        Driver driver = new Driver();
        driver.go(car, address);

    }
}
