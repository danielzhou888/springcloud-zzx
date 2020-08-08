package com.zzx.design.pattern.zzxdesignpattern.mashibin.thinking_in_oo.chapter1;

/**
 * @author zhouzhixiang
 * @Date 2020-04-20
 */
public class Main {

    public static void main(String[] args) {
        // 开车 如何开  到哪里去   CarManager  -> preGo() --> go()
        // 开车前要检查油量，带安全带 preGo
        // 什么牌子的车  Brand
        // 谁在开车  Driver
        Vehicle vehicle = new Car(new Brand(1, "宝马"), new CarManner());
        Driver driver = new Driver("小明");
        Address address = new Address(1, "上海");
        driver.go(vehicle, address);
    }
}
