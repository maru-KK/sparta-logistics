package com.sparta.logistics.order.presentation.rest.util.search;

import com.sparta.logistics.order.infrastructure.persistence.search.OrderSearchCondition;
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
            parameter.getParameterType().equals(OrderSearchCondition.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        return extractSearchCondition(webRequest);
    }

    private OrderSearchCondition extractSearchCondition(NativeWebRequest webRequest) {

        String page = webRequest.getParameter("page");
        String size = webRequest.getParameter("size");
        String sort = webRequest.getParameter("sort");

        String supplier = webRequest.getParameter("supplier");
        String consumer = webRequest.getParameter("consumer");
        String status = webRequest.getParameter("status");

        return OrderSearchCondition.of(page, size, sort, supplier, consumer, status);
    }
}
