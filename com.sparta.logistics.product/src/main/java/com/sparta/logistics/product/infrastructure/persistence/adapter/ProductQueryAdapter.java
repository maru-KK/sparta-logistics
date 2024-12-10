package com.sparta.logistics.product.infrastructure.persistence.adapter;

import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.infrastructure.persistence.entity.ProductEntity;
import com.sparta.logistics.product.infrastructure.persistence.repository.ProductQueryDslRepository;
import com.sparta.logistics.product.infrastructure.persistence.repository.ProductQueryRepository;
import com.sparta.logistics.product.infrastructure.persistence.search.ProductSearchCondition;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductQueryAdapter {

    private final ProductQueryRepository productRepository;
    private final ProductQueryDslRepository productQueryDslRepository;

    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId)
            .map(ProductEntity::toDomain)
            .or(Optional::empty);
    }

    public Page<Product> search(ProductSearchCondition condition) {
        return productQueryDslRepository.findAll(condition)
            .map(ProductEntity::toDomain);
    }
}
