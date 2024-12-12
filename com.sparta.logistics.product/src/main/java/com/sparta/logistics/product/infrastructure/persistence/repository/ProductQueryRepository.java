package com.sparta.logistics.product.infrastructure.persistence.repository;

import com.sparta.logistics.product.infrastructure.persistence.entity.ProductEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductQueryRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM product p WHERE p.id = :productId AND p.isDeleted = false")
    Optional<ProductEntity> findById(@Param("productId") Long productId);
}
