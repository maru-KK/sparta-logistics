package com.sparta.logistics.order.presentation.rest.dto.commnad;

import com.sparta.logistics.order.domain.order.Order;
import com.sparta.logistics.order.domain.order.OrderForCreate;
import jakarta.validation.constraints.Future;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderCreation {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Long orderedCompany;
        private String requestMessage;

        @Future
        private LocalDateTime deliveryLimitedAt;

        private Long productId;
        private Integer orderedQuantity;

        public OrderForCreate toDomain(Long orderedBy) {
            return new OrderForCreate(
                orderedBy,
                orderedCompany,
                productId,
                orderedQuantity,
                requestMessage,
                (deliveryLimitedAt == null) ? LocalDateTime.now().plusDays(14) : deliveryLimitedAt
            );
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long OrderId;
        private Long orderedBy;

        public static Response from(Order order) {
            return new Response(order.getId(), order.getOrderedBy());
        }
    }
}
