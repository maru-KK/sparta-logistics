package com.sparta.logistics.product.infrastructure.persistence.repository;

import com.sparta.logistics.product.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommandRepository extends JpaRepository<ProductEntity, Long> {

}
