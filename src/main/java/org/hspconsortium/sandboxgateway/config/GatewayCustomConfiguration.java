package org.hspconsortium.sandboxgateway.config;

import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.cloud.gateway.handler.FilteringWebHandler;
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayCustomConfiguration extends GatewayAutoConfiguration {

    @Bean
    public RoutePredicateHandlerMapping routePredicateHandlerMapping(FilteringWebHandler webHandler,
                                                                     RouteLocator routeLocator, GlobalCorsProperties globalCorsProperties, Environment environment) {

        return new RoutePredicateHandlerMapping(webHandler, routeLocator, globalCorsProperties, environment){

            public Mono<Object> getHandler(ServerWebExchange exchange) {
                Mono<Object> handler = getHandlerInternal(exchange).map((hndl) -> {return hndl;});
                return CorsUtils.isPreFlightRequest(exchange.getRequest()) ? handler : super.getHandler(exchange);
            }
        };
    }
}