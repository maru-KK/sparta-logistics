package com.sparta.logistics.product.presentation.rest.dto.command;

import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.ProductForCreate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductCreation {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private String name;
        private Integer quantity;

        public ProductForCreate toDomain(Long createdBy) {
            return new ProductForCreate(name, quantity, createdBy);
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long id;
        private String name;
        private Integer quantity;

        public static Response from(Product product) {
            return new Response(product.getId(), product.getName(), product.getQuantity());
        }
    }
}
