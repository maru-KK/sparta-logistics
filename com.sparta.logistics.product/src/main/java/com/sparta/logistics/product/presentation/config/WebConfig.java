package com.sparta.logistics.product.presentation.config;

import com.sparta.logistics.product.presentation.rest.util.actor.LoginActorArgumentResolver;
import com.sparta.logistics.product.presentation.rest.util.search.SearchConditionArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
