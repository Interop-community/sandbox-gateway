package org.hspconsortium.sandboxgateway.config;

import com.fasterxml.jackson.databind.JsonNode;
import org.hl7.fhir.instance.model.Bundle;
import org.hspconsortium.sandboxgateway.filter.FhirEndpointResolutionFilter;
import org.hspconsortium.sandboxgateway.service.FhirEndpointResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
public class RouteConfig {

    @Autowired
    private FhirEndpointResolutionService fhirEndpointResolutionService;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/**")
                        .filters(f -> f.filter(new FhirEndpointResolutionFilter().apply(new FhirEndpointResolutionFilter.Config(fhirEndpointResolutionService)))
                                .removeRequestHeader("accept-encoding")
                                .removeRequestHeader("accept")
                                .addRequestHeader("accept", "application/json")
                                .modifyResponseBody(String.class, String.class, MediaType.TEXT_PLAIN_VALUE,
                                        (exchange, bundle) -> {
                                            bundle = bundle.replaceAll("http://localhost:12100", "googoo");
                                            return Mono.just(bundle);
                                        }))
                        .uri("http://httpbin.org:80"))
                .build();
    }
}
