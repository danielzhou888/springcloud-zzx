package com.zzx.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.zzx.mybatis.dao.DeptMapper;
import com.zzx.mybatis.dao.UserMapper;
import com.zzx.mybatis.model.User;
import com.zzx.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Zhou / zzx
 * @Date 2019-05-26 23:15
 **/
@Service(value = "userServcie")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }
}
