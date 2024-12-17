package com.sparta.logistics.hubcompany.presentation.util.search;

import com.sparta.logistics.hubcompany.infrastructure.persistence.search.HubSearchCondition;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class SearchConditionArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SearchCondition.class) &&
            parameter.getParameterType().equals(HubSearchCondition.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        return extractSearchCondition(webRequest);
    }

    private HubSearchCondition extractSearchCondition(NativeWebRequest webRequest) {

        String page = webRequest.getParameter("page");
        String size = webRequest.getParameter("size");
        String sort = webRequest.getParameter("sort");
        String keyword = webRequest.getParameter("keyword");

        return HubSearchCondition.of(page, size, sort, keyword);
    }
}
