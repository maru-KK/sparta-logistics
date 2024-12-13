package com.sparta.logistics.gateway.filter;

import static com.sparta.logistics.gateway.filter.AccessTokenAuthenticationFilter.AUTHENTICATION_FILTER_ORDER;
import static com.sparta.logistics.gateway.filter.AccessTokenAuthenticationFilter.HEADER_ROLE;

import com.sparta.logistics.gateway.util.RolePermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AccessAuthorizationFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        HttpMethod method = exchange.getRequest().getMethod();
        String role = exchange.getRequest().getHeaders().getFirst(HEADER_ROLE);

        if (!RolePermission.isAllowed(role, path, method)) {
            return handleAccessDenied(exchange);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> handleAccessDenied(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        log.warn("Access denied for path: {}, method: {}",
            exchange.getRequest().getURI().getPath(),
            exchange.getRequest().getMethod());
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return AUTHENTICATION_FILTER_ORDER + 1;
    }
}
