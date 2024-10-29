package com.zzx.design.pattern.zzxdesignpattern.sample.builder_mode.create_complex_user_config;

import java.util.List;

/**
 * @Description: 用户配置文件对象
 * @author: 周志祥
 * @create: 2024-10-29 11:17
 */
public class UserProfile {

    private String userName;
    private String password;
    private String email;
    private String bio;
    private String avatar;
    private List<String> interests;

    public UserProfile(String userName, String password, String email, String bio, String avatar, List<String> interests) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.avatar = avatar;
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", avatar='" + avatar + '\'' +
                ", interests=" + interests +
                '}';
    }
}
