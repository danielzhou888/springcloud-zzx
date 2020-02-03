package com.zzx.spring.zzxspring.service;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
public interface RemoteSystemAuthService {
    Set<String> selectAuthsByUserId(@PathVariable("userId") Long userId);
}
