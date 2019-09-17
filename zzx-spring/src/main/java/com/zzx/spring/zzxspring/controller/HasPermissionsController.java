package com.zzx.spring.zzxspring.controller;

import com.zzx.spring.zzxspring.feign.RemoteSystemAuthFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
@RestController
@RequestMapping("/hasPermissions")
public class HasPermissionsController {

    @Autowired
    private RemoteSystemAuthFeign remoteSystemAuthFeign;

    @GetMapping("/roleList/{userId}")
    public Mono<Object> getRoleListByUserId(@PathVariable("userId") Long userId) {
        Set<String> strings = remoteSystemAuthFeign.selectAuthsByUserId(userId);
        return Mono.just(strings);
    }
}
