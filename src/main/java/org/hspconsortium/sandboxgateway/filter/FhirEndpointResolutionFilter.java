package org.hspconsortium.sandboxgateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hspconsortium.sandboxgateway.service.FhirEndpointResolutionService;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

public class FhirEndpointResolutionFilter extends AbstractGatewayFilterFactory<FhirEndpointResolutionFilter.Config> {

    private Log log = LogFactory.getLog(FhirEndpointResolutionFilter.class);

    @Override
    public GatewayFilter apply(FhirEndpointResolutionFilter.Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getPath().toString();
            String sandboxId = path.substring(path.indexOf("/") + 1, path.indexOf("/", path.indexOf("/") + 1));
            String host = config.getFhirEndpointResolutionService().getHost(sandboxId);
            updateRouteUri(exchange, host);
            return chain.filter(exchange);
        };
    }

    public void updateRouteUri(ServerWebExchange exchange, String newUri) {

        Route route = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(route.getId());
        routeDefinition.setOrder(route.getOrder());
        routeDefinition.setUri(URI.create(newUri));

        DynamicUriRouteBuilder myNewRouteBuilder = DynamicUriRouteBuilder.builder(routeDefinition);
        myNewRouteBuilder.setPredicate(route.getPredicate());

        exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR, myNewRouteBuilder.build());
    }

    static class DynamicUriRouteBuilder extends Route.AsyncBuilder {

        public static DynamicUriRouteBuilder builder(RouteDefinition routeDefinition) {
            return (DynamicUriRouteBuilder)((DynamicUriRouteBuilder)((DynamicUriRouteBuilder)(new DynamicUriRouteBuilder()).id(routeDefinition.getId())).uri(routeDefinition.getUri())).order(routeDefinition.getOrder());
        }

        public void setPredicate(AsyncPredicate<ServerWebExchange> predicate){
            this.predicate = predicate;
        }
    }

    public static class Config {
        private FhirEndpointResolutionService fhirEndpointResolutionService;

        public Config(FhirEndpointResolutionService fhirEndpointResolutionService) {
            this.fhirEndpointResolutionService = fhirEndpointResolutionService;
        }

        public FhirEndpointResolutionService getFhirEndpointResolutionService() {
            return fhirEndpointResolutionService;
        }

        public void setFhirEndpointResolutionService(FhirEndpointResolutionService fhirEndpointResolutionService) {
            this.fhirEndpointResolutionService = fhirEndpointResolutionService;
        }
    }

}

