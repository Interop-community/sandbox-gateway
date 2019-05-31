package org.hspconsortium.sandboxgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class CorsConfig {

    private static final List<String> ALLOWED_HEADERS = new ArrayList<>();
    private static final List<HttpMethod> ALLOWED_METHODS = new ArrayList<>();
    private static final String ALLOWED_ORIGIN = "*";
    private static final long MAX_AGE = 3600;

    @Bean
    public WebFilter corsFilter() {

        ALLOWED_METHODS.add(HttpMethod.GET);
        ALLOWED_METHODS.add(HttpMethod.PUT);
        ALLOWED_METHODS.add(HttpMethod.POST);
        ALLOWED_METHODS.add(HttpMethod.DELETE);
        ALLOWED_METHODS.add(HttpMethod.OPTIONS);
        ALLOWED_METHODS.add(HttpMethod.PATCH);

        ALLOWED_HEADERS.add("X-FHIR-Starter");
        ALLOWED_HEADERS.add("authorization");
        ALLOWED_HEADERS.add("Prefer");
        ALLOWED_HEADERS.add("Origin");
        ALLOWED_HEADERS.add("Accept");
        ALLOWED_HEADERS.add("X-Requested-With");
        ALLOWED_HEADERS.add("Content-Type");
        ALLOWED_HEADERS.add("Access-Control-Request-Method");
        ALLOWED_HEADERS.add("Access-Control-Request-Headers");

        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.setAccessControlAllowOrigin(ALLOWED_ORIGIN);
                headers.setAccessControlAllowMethods(ALLOWED_METHODS);
                headers.setAccessControlMaxAge(MAX_AGE);
                headers.setAccessControlAllowHeaders(ALLOWED_HEADERS);
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }

}