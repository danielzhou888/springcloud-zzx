package com.zzx.spring.zzxspring.feign;

import com.zzx.spring.zzxspring.fallback.RemoteSystemAuthFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
@FeignClient(value = "zzx-system", fallback = RemoteSystemAuthFallbackFactory.class)
public interface RemoteSystemAuthFeign {

    @GetMapping("/system/auth/{userId}")
    Set<String> selectAuthsByUserId(@PathVariable("userId") Long userId);
}
