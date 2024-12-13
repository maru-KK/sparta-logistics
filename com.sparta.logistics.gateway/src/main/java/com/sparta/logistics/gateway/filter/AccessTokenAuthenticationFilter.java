package com.sparta.logistics.gateway.filter;

import com.sparta.logistics.gateway.util.tokenprovider.AccessTokenProvider;
import com.sparta.logistics.gateway.util.tokenprovider.Actor;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccessTokenAuthenticationFilter implements GlobalFilter, Ordered {

    public static final int AUTHENTICATION_FILTER_ORDER = 100;
    public static final List<String> publicPaths = List.of("/api/v1/auth/sign-in", "/api/v1/auth/sign-up");
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_USER_ID = "X-User-Id";
    public static final String HEADER_ROLE = "X-Role";

    private final AccessTokenProvider tokenProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (isPublicUri(path)) {
            return chain.filter(exchange);
        }
        return checkAccessToken(exchange, chain);
    }

    private boolean isPublicUri(String requestPath) {
        for (String publicPath : publicPaths) {
            if (Objects.equals(publicPath, requestPath)) {
                return true;
            }
        }
        return false;
    }

    private Mono<Void> checkAccessToken(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HEADER_AUTHORIZATION);
        Actor actor = tokenProvider.readToken(authHeader);

        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
            .header(HEADER_USER_ID, String.valueOf(actor.userId()))
            .header(HEADER_ROLE, String.valueOf(actor.role()))
            .build();

        ServerWebExchange mutatedExchange = exchange.mutate()
            .request(mutatedRequest)
            .build();

        return chain.filter(mutatedExchange);
    }

    @Override
    public int getOrder() {
        return AUTHENTICATION_FILTER_ORDER;
    }
}
