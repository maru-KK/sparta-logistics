package com.sparta.logistics.delivery.presentation.config;

import com.sparta.logistics.delivery.presentation.util.actor.LoginActorArgumentResolver;
import com.sparta.logistics.delivery.presentation.util.search.SearchConditionArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SearchConditionArgumentResolver searchConditionArgumentResolver;
    private final LoginActorArgumentResolver loginActorArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(searchConditionArgumentResolver);
        resolvers.add(loginActorArgumentResolver);
    }
}
