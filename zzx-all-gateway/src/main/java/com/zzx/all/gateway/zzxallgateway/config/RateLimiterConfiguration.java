package com.zzx.all.gateway.zzxallgateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
@Configuration
public class RateLimiterConfiguration {

    @Bean(name = "remoteAddrKeyResolver")
    public KeyResolver remoteAddrKeyResolver() {
        return new RemoteAddrKeyResolver();
    }

    @Bean("remoteAddrKeyResolver")
    public KeyResolver remoteAddrKeyResolver2() {
        return (exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()));
    }
}
