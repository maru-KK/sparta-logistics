package com.sparta.logistics.product.infrastructure.persistence.entity;

import com.sparta.logistics.product.domain.Company;
import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.ProductForCreate;
import com.sparta.logistics.product.domain.vo.Quantity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_product")
@Entity(name = "product")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long hubId;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    public ProductEntity(Long id, Long hubId, Long companyId, String name, Integer quantity, Long createdBy) {
        super();
        super.createdFrom(createdBy);

        this.id = id;
        this.hubId = hubId;
        this.companyId = companyId;
        this.name = name;
        this.quantity = quantity;
    }

    public Product toDomain() {
        return new Product(
            id, hubId, companyId, Quantity.valueOf(quantity), name
        );
    }

    public static ProductEntity of(ProductForCreate product, Company company) {
        return new ProductEntity(
            null,
            company.getHubId(),
            company.getId(),
            product.getName(),
            product.getQuantity(),
            product.getCreatedBy()
        );
    }

    public ProductEntity updateFrom(Product product, Long updatedBy) {
        super.updatedFrom(updatedBy);
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.hubId = product.getHubId();
        this.companyId = product.getCompanyId();

        return this;
    }
}
