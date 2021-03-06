package com.zzx.all.gateway.zzxallgateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 叮当快药科技集团有限公司</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class RemoteAddrKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        Mono<String> just = Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
        return just;
    }
}
