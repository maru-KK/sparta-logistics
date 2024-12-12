package com.sparta.logistics.order.presentation.rest.dto.query;

import com.sparta.logistics.order.domain.order.Product;
import com.sparta.logistics.order.domain.order.vo.OrderProduct;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {

    private Long id;

    private Long orderedBy;
    private Long consumeCompanyId;
    private String requestMessage;
    private LocalDateTime deliveryLimitedAt;
    private String status;
    private Integer orderedQuantity;
    private ProductResponse product;

    public static OrderDetailResponse from(OrderProduct orderProduct) {
        return new OrderDetailResponse(
            orderProduct.getId(), orderProduct.getOrderedBy(), orderProduct.getConsumeCompanyId(),
            orderProduct.getRequestMessage(), orderProduct.getDeliveryLimitedAt(),
            orderProduct.getStatus().name(), orderProduct.getOrderedQuantity(),
            ProductResponse.from(orderProduct.getProduct())
        );
    }

    @Getter
    @AllArgsConstructor
    private static class ProductResponse {

        private Long id;
        private String name;
        private Integer remainingQuantity;
        private Long supplyCompany;
        private Long supplyHub;

        private static ProductResponse from(Product product) {
            return new ProductResponse(
                product.getId(), product.getName(), product.getQuantity(),
                product.getSupplyCompany(), product.getSupplyHub()
            );
        }
    }
}
