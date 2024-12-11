package com.sparta.logistics.product.presentation.rest.dto.query;

import com.sparta.logistics.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {

    private Long id;
    private Long hubId;
    private Long companyId;
    private Integer quantity;
    private String name;

    public static ProductDetailResponse from(Product product) {
        return new ProductDetailResponse(
            product.getId(),
            product.getHubId(),
            product.getCompanyId(),
            product.getQuantity(),
            product.getName()
        );
    }
}
