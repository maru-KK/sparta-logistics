package com.sparta.logistics.product.infrastructure.cache.dto;

import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.vo.Quantity;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCache implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final String KEY_FORMAT = "product:%d";

    private Long id;
    private Long hubId;
    private Long companyId;
    private Integer quantity;
    private String name;

    public Product toDomain() {
        return new Product(
            id, hubId, companyId, Quantity.valueOf(quantity), name
        );
    }

    public static ProductCache from(Product product) {
        return new ProductCache(
            product.getId(),
            product.getHubId(),
            product.getCompanyId(),
            product.getQuantity(),
            product.getName()
        );
    }

    public String key() {
        return String.format(KEY_FORMAT, id);
    }
}
