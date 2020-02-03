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
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
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
