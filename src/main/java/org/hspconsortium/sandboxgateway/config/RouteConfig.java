package org.hspconsortium.sandboxgateway.config;

import org.hspconsortium.sandboxgateway.filter.FhirEndpointResolutionFilter;
import org.hspconsortium.sandboxgateway.service.FhirEndpointResolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

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
                                .modifyResponseBody(String.class, String.class, MediaType.TEXT_PLAIN_VALUE,
                                        (exchange, bundle) -> {
                                            String path = exchange.getRequest().getPath().toString();
                                            String sandboxId = path.substring(path.indexOf("/") + 1, path.indexOf("/", path.indexOf("/") + 1));
                                            bundle = bundle.replaceAll(fhirEndpointResolutionService.getHost(sandboxId), fhirEndpointResolutionService.getApiUrl());
                                            return Mono.just(bundle);
                                        }))
                        .uri(fhirEndpointResolutionService.getApiUrl()))
                .build();
    }
}
