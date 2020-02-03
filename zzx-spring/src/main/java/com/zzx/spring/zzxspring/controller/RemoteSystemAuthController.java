package com.zzx.spring.zzxspring.controller;

import com.zzx.spring.zzxspring.service.RemoteSystemAuthService;
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
@RequestMapping("/system/auth")
public class RemoteSystemAuthController {

    @Autowired
    private RemoteSystemAuthService remoteSystemAuthService;

    @GetMapping("/roleList/{userId}")
    public Mono<Object> getRoleListByUserId(@PathVariable("userId") Long userId) {
        Set<String> strings = remoteSystemAuthService.selectAuthsByUserId(userId);
        return Mono.just(strings);
    }
}
