package com.zzx.design.pattern.zzxdesignpattern.sample.singleton_mode.log_manage_system;

/**
 * @Description: 用户模块Service
 * @author: 周志祥
 * @create: 2024-10-12 15:31
 */
public class UserService {

    public void createUser(String userName) {
        LogManager logManager = LogManager.getInstance();
        logManager.log("创建用户：" + userName);
    }
}
