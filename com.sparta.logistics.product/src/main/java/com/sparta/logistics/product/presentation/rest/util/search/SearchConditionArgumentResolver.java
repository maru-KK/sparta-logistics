package com.sparta.logistics.product.presentation.rest.util.search;

import com.sparta.logistics.product.infrastructure.persistence.search.ProductSearchCondition;
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
            parameter.getParameterType().equals(ProductSearchCondition.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        return extractSearchCondition(webRequest);
    }

    private ProductSearchCondition extractSearchCondition(NativeWebRequest webRequest) {

        String page = webRequest.getParameter("page");
        String size = webRequest.getParameter("size");
        String sort = webRequest.getParameter("sort");
        String hubId = webRequest.getParameter("hub");
        String companyId = webRequest.getParameter("company");
        String keyword = webRequest.getParameter("keyword");

        return ProductSearchCondition.of(page, size, sort, hubId, companyId, keyword);
    }
}
