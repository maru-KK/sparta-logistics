package com.sparta.logistics.product.infrastructure.persistence.entity;

import com.sparta.logistics.product.domain.Product;
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

    public Product toDomain() {
        return new Product(
            id, hubId, companyId, Quantity.valueOf(quantity), name
        );
    }
}
