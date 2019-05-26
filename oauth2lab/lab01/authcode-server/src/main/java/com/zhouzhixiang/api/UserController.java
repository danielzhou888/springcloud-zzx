package com.zhouzhixiang.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-22 18:55
 */
@RestController
public class UserController {

    /**
     * 资源api
     */
    @GetMapping("/api/userinfo")
    public ResponseEntity<UserInfo> getUserInfo() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername() + "@zhouzhixiang.com";

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setName(user.getUsername());
        return ResponseEntity.ok(userInfo);
    }
}
