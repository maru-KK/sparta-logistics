package com.sparta.logistics.delivery.presentation.util.search;

import com.sparta.logistics.delivery.infrastructure.persistence.search.DeliverySearchCondition;
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
                parameter.getParameterType().equals(DeliverySearchCondition.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        return extractSearchCondition(webRequest);
    }

    private DeliverySearchCondition extractSearchCondition(NativeWebRequest webRequest) {

        return DeliverySearchCondition.of(
                webRequest.getParameter("page"),
                webRequest.getParameter("size"),
                webRequest.getParameter("sort"),
                webRequest.getParameter("status"),
                webRequest.getParameter("deliveryAddress"),
                webRequest.getParameter("recipientName"),
                webRequest.getParameter("recipientSnsId"),
                webRequest.getParameter("orderId"),
                webRequest.getParameter("originHubId"),
                webRequest.getParameter("destinationHubId"),
                webRequest.getParameter("keyword")
        );
    }
}
