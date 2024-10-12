package com.zzx.design.pattern.zzxdesignpattern.sample.singleton_mode.log_manage_system;

/**
 * https://app.yinxiang.com/fx/c92e5b7e-e623-46df-9d76-27c19ed75090
 * @Description: 单例模式测试类
 * @author: 周志祥
 * @create: 2024-10-12 15:32
 */
public class SingletonPatternDemo {

    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.createUser("张三");

        OrderService orderService = new OrderService();
        orderService.createOrder("HJ289287887838");
    }
}
