package com.zzx.mybatis.controller;

import com.zzx.mybatis.model.User;
import com.zzx.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Copyright All Rights Reserved
 * @Company 叮当快药科技集团有限公司
 * @Author Zhou / zzx
 * @Date 2019-05-26 23:13
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping(value = "/getAllUser/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public List<User> getAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

}
