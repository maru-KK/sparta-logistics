package com.sparta.logistics.product.presentation.rest.dto.command;

import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.vo.ProductForUpdate;
import com.sparta.logistics.product.domain.vo.Quantity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductModification {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Long productId;
        private String name;
        private Integer quantity;

        public ProductForUpdate toDomain() {
            return new ProductForUpdate(productId, name, Quantity.valueOf(quantity));
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
