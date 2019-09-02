package com.zzx.spring.zzxspring.feign;

import com.zzx.spring.zzxspring.fallback.RemoteSystemAuthFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
@FeignClient(value = "zzx-system", fallback = RemoteSystemAuthFallbackFactory.class)
public interface RemoteSystemAuth {

    @GetMapping("/system/auth/{userId}")
    Set<String> selectAuthsByUserId(@PathVariable("userId") Long userId);
}
