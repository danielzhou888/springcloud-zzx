package gateway.config;

import gateway.filter.RequestTimeFilter;
import gateway.filter.RequestTimeGatewayFilterFactory;
import gateway.filter.TokenFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springcloud-zzx
 * @description:
 * @author: zhouzhixiang
 * @create: 2019-04-08 17:03
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/customer/**")
                    .filters(f -> f.filter(new RequestTimeFilter())
                        .addResponseHeader("X-Response-Default-Foo", "Bar"))
                    .uri("http://httpbin.org:80/get")
                    .order(0)
                    .id("customer_filter_router")
                )
                .build();
    }

    @Bean
    public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new RequestTimeGatewayFilterFactory();
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}
