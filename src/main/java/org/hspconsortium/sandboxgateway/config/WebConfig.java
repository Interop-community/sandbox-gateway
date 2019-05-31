//package org.hspconsortium.sandboxgateway.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.config.CorsRegistry;
//import org.springframework.web.reactive.config.EnableWebFlux;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//
//@Configuration
//@EnableWebFlux
//public class WebConfig implements WebFluxConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH")
//                .allowedOrigins("*")
//                .allowedHeaders("X-FHIR-Starter", "authorization", "Prefer", "Origin", "Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers");
//    }
//}
