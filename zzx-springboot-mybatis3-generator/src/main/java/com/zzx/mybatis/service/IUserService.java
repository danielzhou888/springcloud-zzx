package com.zzx.mybatis.service;

import com.zzx.mybatis.model.User;

import java.util.List;

/**
 * @Copyright All Rights Reserved
 * @Company 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/
 * @Author Zhou / zzx
 * @Date 2019-05-26 23:14
 **/
public interface IUserService {

    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}
