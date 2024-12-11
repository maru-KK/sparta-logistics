package com.sparta.logistics.order.presentation.rest.util.actor;

import com.sparta.logistics.order.presentation.rest.dto.security.Actor;
import com.sparta.logistics.order.presentation.rest.dto.security.Role;
import com.sparta.logistics.order.presentation.rest.exception.exceptions.InvalidAccessResourceException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginActorArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String HEADER_USER_ID = "X-User-Id";
    private static final String HEADER_ROLE = "X-Role";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginActor.class) &&
            parameter.getParameterType().equals(Actor.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Long userId = extractUserId(webRequest);
        Role role = extractRole(webRequest);

        return new Actor(userId, role);
    }

    private Long extractUserId(NativeWebRequest webRequest) {
        String userId = webRequest.getHeader(HEADER_USER_ID);
        try {
            return Long.parseLong(userId);
        } catch (NumberFormatException exception) {
            throw new InvalidAccessResourceException("비정상적인 유저 접근");
        }
    }

    private Role extractRole(NativeWebRequest webRequest) {
        String role = webRequest.getHeader(HEADER_ROLE);
        try {
            return Role.valueOf(role);

        // if role mismatch
        } catch (IllegalArgumentException exception) {
            throw new InvalidAccessResourceException("비정상적인 유저 접근");
        }
    }
}
