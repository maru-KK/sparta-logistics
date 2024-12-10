package com.sparta.logistics.product.infrastructure.persistence.adapter;

import com.sparta.logistics.product.application.outputport.ProductOutputPort;
import com.sparta.logistics.product.domain.Company;
import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.ProductForCreate;
import com.sparta.logistics.product.infrastructure.persistence.entity.ProductEntity;
import com.sparta.logistics.product.infrastructure.persistence.repository.ProductCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductCommandAdapter implements ProductOutputPort {

    private final ProductCommandRepository productCommandRepository;

    @Override
    public Product saveOne(ProductForCreate product, Company company) {
        ProductEntity productEntity = ProductEntity.of(product, company);
        return productCommandRepository.save(productEntity).toDomain();
    }
}
