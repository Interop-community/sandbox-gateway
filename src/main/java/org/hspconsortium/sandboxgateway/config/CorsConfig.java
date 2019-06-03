//package org.hspconsortium.sandboxgateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//import org.springframework.web.reactive.config.CorsRegistry;
//import org.springframework.web.reactive.config.EnableWebFlux;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//
//import java.util.Arrays;
//
//
//@Configuration
//@EnableWebFlux
//public class CorsConfig implements WebFluxConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry corsRegistry) {
//        corsRegistry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowCredentials(true)
////                .exposedHeaders("Access-Control-Allow-Origin",
////                        "Access-Control-Allow-Methods",
////                        "Access-Control-Allow-Headers",
////                        "Access-Control-Max-Age",
////                        "Access-Control-Request-Headers",
////                        "Access-Control-Request-Method")
//                .maxAge(3600);
//    }

//    @Bean
//    CorsWebFilter corsWebFilter() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowCredentials(true);
//        corsConfig.addAllowedOrigin("*");
//        corsConfig.addAllowedHeader("*");
//        corsConfig.addAllowedMethod("*");
//        corsConfig.setMaxAge(8000L);
//
////        corsConfig.setAllowedOrigins(Arrays.asList("*"));
////        corsConfig.setMaxAge(8000L);
////        corsConfig.addAllowedMethod("*");
////        corsConfig.addAllowedHeader("*");
////        corsConfig.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return new CorsWebFilter(source);
//    }
//}
//
//


//        import org.springframework.context.annotation.Bean;
//        import org.springframework.http.HttpHeaders;
//        import org.springframework.http.HttpMethod;
//        import org.springframework.http.HttpStatus;
//        import org.springframework.http.server.reactive.ServerHttpRequest;
//        import org.springframework.http.server.reactive.ServerHttpResponse;
//        import org.springframework.web.cors.reactive.CorsUtils;
//        import org.springframework.web.server.ServerWebExchange;
//        import org.springframework.web.server.WebFilter;
//        import org.springframework.web.server.WebFilterChain;
//
//        import reactor.core.publisher.Mono;
//
//        import java.util.ArrayList;
//        import java.util.List;

//@Configuration
//public class CorsConfig {
//
//    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN";
//    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS";
//    private static final String ALLOWED_ORIGIN = "*";
//    private static final String MAX_AGE = "3600";
//
//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            if (CorsUtils.isCorsRequest(request)) {
//                ServerHttpResponse response = ctx.getResponse();
//                HttpHeaders headers = response.getHeaders();
//                headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
//                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
//                headers.add("Access-Control-Max-Age", MAX_AGE);
//                headers.add("Access-Control-Allow-Headers",ALLOWED_HEADERS);
//                if (request.getMethod() == HttpMethod.OPTIONS) {
//                    response.setStatusCode(HttpStatus.OK);
//                    return Mono.empty();
//                }
//            }
//            return chain.filter(ctx);
//        };
//    }
//
//}

//    private static final List<String> ALLOWED_HEADERS = new ArrayList<>();
//    private static final List<HttpMethod> ALLOWED_METHODS = new ArrayList<>();
//    private static final String ALLOWED_ORIGIN = "*";
//    private static final long MAX_AGE = 3600;

//                ALLOWED_METHODS.add(HttpMethod.GET);
//                ALLOWED_METHODS.add(HttpMethod.PUT);
//                ALLOWED_METHODS.add(HttpMethod.POST);
//                ALLOWED_METHODS.add(HttpMethod.DELETE);
//                ALLOWED_METHODS.add(HttpMethod.OPTIONS);
//                ALLOWED_METHODS.add(HttpMethod.PATCH);
//
//                ALLOWED_HEADERS.add("X-FHIR-Starter");
//                ALLOWED_HEADERS.add("authorization");
//                ALLOWED_HEADERS.add("Prefer");
//                ALLOWED_HEADERS.add("Origin");
//                ALLOWED_HEADERS.add("Accept");
//                ALLOWED_HEADERS.add("X-Requested-With");
//                ALLOWED_HEADERS.add("Content-Type");
//                ALLOWED_HEADERS.add("Access-Control-Request-Method");
//                ALLOWED_HEADERS.add("Access-Control-Request-Headers");
//
//          @Bean
//          public WebFilter corsFilter() {
//                return (ServerWebExchange ctx, WebFilterChain chain) -> {
//                ServerHttpRequest request = ctx.getRequest();
//                if (CorsUtils.isCorsRequest(request)) {
//                ServerHttpResponse response = ctx.getResponse();
//                HttpHeaders headers = response.getHeaders();
//                headers.setAccessControlAllowOrigin(ALLOWED_ORIGIN);
//                headers.setAccessControlAllowMethods(ALLOWED_METHODS);
//                headers.setAccessControlMaxAge(MAX_AGE);
//                headers.setAccessControlAllowHeaders(ALLOWED_HEADERS);
//                if (request.getMethod() == HttpMethod.OPTIONS) {
//                response.setStatusCode(HttpStatus.OK);
//                return Mono.empty();
//                }
//                }
//                return chain.filter(ctx);
//                };
//                }