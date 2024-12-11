package com.sparta.logistics.product.presentation.rest.dto.command;

import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.vo.ProductForUpdateQuantity;
import com.sparta.logistics.product.domain.vo.Quantity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ProductQuantityModification {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Long productId;
        private Integer changedCount;
        private Boolean isDecrease;

        public ProductForUpdateQuantity toDomain() {
            return new ProductForUpdateQuantity(productId, Quantity.valueOf(changedCount), isDecrease);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long id;
        private String name;
        private Integer quantity;

        public static ProductCreation.Response from(Product product) {
            return new ProductCreation.Response(product.getId(), product.getName(), product.getQuantity());
        }
    }
}
