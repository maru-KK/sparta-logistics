package com.sparta.logistics.product.infrastructure.persistence.entity;

import com.sparta.logistics.product.application.exception.ProductLogicException;
import com.sparta.logistics.product.domain.Company;
import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.vo.ProductForCreate;
import com.sparta.logistics.product.domain.vo.ProductForUpdateQuantity;
import com.sparta.logistics.product.domain.vo.Quantity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
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

    @Version
    @Column(nullable = false)
    private Long version;

    public ProductEntity(Long createdBy, Long id, Long hubId, Long companyId, String name, Integer quantity) {
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
            product.getCreatedBy(),
            null,
            company.getHubId(),
            company.getId(),
            product.getName(),
            product.getQuantity()
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

    public ProductEntity changeQuantity(ProductForUpdateQuantity product, Long updatedBy) {;
        if (product.isDecrease()) {
            decreaseQuantity(product.getChangedCount());
            super.updatedFrom(updatedBy);
            return this;
        }
        this.quantity += product.getChangedCount();
        super.updatedFrom(updatedBy);
        return this;
    }

    private void decreaseQuantity(Integer decreaseCount) {
        if (quantity < decreaseCount) {
            throw new ProductLogicException("상품 재고가 충분하지 않습니다.");
        }
        quantity -= decreaseCount;
    }
}
