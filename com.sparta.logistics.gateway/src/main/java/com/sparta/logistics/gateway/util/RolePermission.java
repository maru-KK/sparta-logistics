package com.sparta.logistics.gateway.util;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpMethod;

public class RolePermission {

    private static final Map<String, List<AllowedPath>> PERMISSIONS = Map.of(
        "MASTER", List.of(

        ),
        "HUB", List.of(

        ),
        "COMPANY", List.of(
            AllowedPath.of("/api/v1/products", HttpMethod.POST),
            AllowedPath.of("/api/v1/products/*", HttpMethod.PATCH),
            AllowedPath.of("/api/v1/orders/*", HttpMethod.GET),
            AllowedPath.of("/api/v1/orders", HttpMethod.GET)
        ),
        "DELIVERY", List.of(
            AllowedPath.of("/api/v1/orders/*", HttpMethod.GET),
            AllowedPath.of("/api/v1/orders", HttpMethod.GET)
        )
    );

    // 권한 없이 접근 가능한 URI
    private static final List<AllowedPath> PUBLIC_PERMISSIONS = List.of(
        // auth-service
        AllowedPath.of("/api/v1/auth/sign-up", HttpMethod.POST), // 회원가입
        AllowedPath.of("/api/v1/auth/sign-in", HttpMethod.POST), // 로그인
        AllowedPath.of("/api/v1/auth/user/*", HttpMethod.GET),   // 회원 단건 조회

        // order-service
        AllowedPath.of("/api/v1/orders", HttpMethod.POST),       // 주문 생성

        // product-service
        AllowedPath.of("/api/v1/products/*", HttpMethod.GET),    // 상품 단건 조회
        AllowedPath.of("/api/v1/products", HttpMethod.GET)       // 상품 검색
    );

    public static boolean isAllowed(String role, String path, HttpMethod method) {
        if (isPublicPermission(path, method)) {
            return true;
        }
        return isAllowedPermission(role, path, method);
    }

    private static boolean isPublicPermission(String path, HttpMethod method) {
        return PUBLIC_PERMISSIONS.stream()
            .anyMatch(allowedPath -> allowedPath.matches(path, method));
    }

    private static boolean isAllowedPermission(String role, String path, HttpMethod method) {
        return PERMISSIONS.getOrDefault(role, List.of()).stream()
            .anyMatch(allowedPath -> allowedPath.matches(path, method));
    }

    public static class AllowedPath {
        private final String regexPattern;
        private final String pathPattern; // Path 패턴
        private final HttpMethod method; // HTTP Method

        public AllowedPath(String pathPattern, HttpMethod method) {
            this.pathPattern = pathPattern;
            this.method = method;
            this.regexPattern = convertToRegex(pathPattern);
        }

        public static AllowedPath of(String pathPattern, HttpMethod method) {
            return new AllowedPath(pathPattern, method);
        }

        public boolean matches(String path, HttpMethod method) {
            return this.method == method && path.matches(regexPattern);
        }

        private String convertToRegex(String pathPattern) {
            // Path 패턴을 정규식으로 변환 (e.g., "/user/**" -> "/user/.*")
            return pathPattern
                .replace("**", ".*")
                .replace("*", "[^/]+");
        }
    }
}
