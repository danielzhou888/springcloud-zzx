package com.zzx.spring.zzxspring.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
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
