package com.sparta.logistics.product.infrastructure.persistence.adapter;

import com.sparta.logistics.product.application.exception.ProductLogicException;
import com.sparta.logistics.product.application.outputport.ProductOutputPort;
import com.sparta.logistics.product.domain.Company;
import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.ProductForCreate;
import com.sparta.logistics.product.infrastructure.persistence.entity.ProductEntity;
import com.sparta.logistics.product.infrastructure.persistence.repository.ProductCommandRepository;
import com.sparta.logistics.product.infrastructure.persistence.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ProductCommandAdapter implements ProductOutputPort {

    private final ProductQueryRepository productQueryRepository;
    private final ProductCommandRepository productCommandRepository;

    @Override
    public Product saveOne(ProductForCreate product, Company company) {
        ProductEntity productEntity = ProductEntity.of(product, company);
        return productCommandRepository.save(productEntity).toDomain();
    }

    @Transactional
    @Override
    public Product update(Product product, Long updatedBy) {
        ProductEntity productEntity = productQueryRepository.findById(product.getId())
            .orElseThrow(() -> new ProductLogicException("유효하지 않은 상품 정보"));

        return productEntity.updateFrom(product, updatedBy)
            .toDomain();
    }
}
