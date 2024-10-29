package com.zzx.design.pattern.zzxdesignpattern.sample.builder_mode.create_complex_user_config;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: 周志祥
 * @create: 2024-10-29 14:51
 */
public class UserProfileBuilder {

    private String userName;
    private String password;
    private String email;
    private String bio;
    private String avatar;
    private List<String> interests = new ArrayList<>();

    public UserProfileBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserProfileBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserProfileBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserProfileBuilder setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public UserProfileBuilder setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public UserProfileBuilder addInterest(String interest) {
        this.interests.add(interest);
        return this;
    }

    public UserProfile build() {
        return new UserProfile(userName, password, email, bio, avatar, interests);
    }
}
