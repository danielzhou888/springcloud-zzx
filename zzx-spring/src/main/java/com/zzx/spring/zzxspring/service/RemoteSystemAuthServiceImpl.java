package com.zzx.spring.zzxspring.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
@Service
public class RemoteSystemAuthServiceImpl implements RemoteSystemAuthService {

    @Override
    public Set<String> selectAuthsByUserId(Long userId) {
        Set<String> roleList = new HashSet<>();
        roleList.add("1");
        roleList.add("2");
        roleList.add("3");
        roleList.add("4");
        roleList.add("5");
        return roleList;
    }
}
