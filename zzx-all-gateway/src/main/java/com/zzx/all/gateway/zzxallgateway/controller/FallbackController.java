package com.zzx.all.gateway.zzxallgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public Mono<Map<String, Object>> fallback(ServerWebExchange exchange, Throwable throwable) {
        Map<String, Object> map = new HashMap<>();
        map.put("address", exchange.getRequest().getRemoteAddress().getAddress());
        return Mono.just(map);
    }
}
