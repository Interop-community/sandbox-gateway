package org.hspconsortium.sandboxgateway.config;

import org.hspconsortium.sandboxgateway.filter.FhirEndpointResolutionFilter;
import org.hspconsortium.sandboxgateway.service.FhirEndpointResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Autowired
    private FhirEndpointResolutionService fhirEndpointResolutionService;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/**")
                        .filters(f -> f.filter(new FhirEndpointResolutionFilter().apply(new FhirEndpointResolutionFilter.Config(fhirEndpointResolutionService)), 1))
                        .uri("http://httpbin.org:80"))
                .build();
    }
}
