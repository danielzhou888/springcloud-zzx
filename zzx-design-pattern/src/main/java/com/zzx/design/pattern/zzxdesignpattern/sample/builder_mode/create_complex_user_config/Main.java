package com.zzx.design.pattern.zzxdesignpattern.sample.builder_mode.create_complex_user_config;

/**
 * https://app.yinxiang.com/fx/c3609b09-f4e9-49fc-b615-8ab9c78c82fc
 * @Description: 建造者模式-创建用户配置文件的场景
 * @author: 周志祥
 * @create: 2024-10-29 11:17
 */
public class Main {
    public static void main(String[] args) {
        UserProfileBuilder builder = new UserProfileBuilder();
        UserProfile userProfile = builder.addInterest("打球")
                .addInterest("游泳")
                .setBio("周志祥博客")
                .setUserName("zhouzhixiang")
                .setPassword("123456")
                .setEmail("zhouzhixiang@163.com")
                .setAvatar("xxxxx")
                .build();
        System.out.println(userProfile);
    }
}
