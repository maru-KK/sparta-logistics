package com.sparta.logistics.product.infrastructure.persistence.adapter;

import com.sparta.logistics.product.application.exception.ProductLogicException;
import com.sparta.logistics.product.application.outputport.ProductOutputPort;
import com.sparta.logistics.product.domain.Company;
import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.vo.ProductForCreate;
import com.sparta.logistics.product.domain.vo.ProductForUpdateQuantity;
import com.sparta.logistics.product.infrastructure.persistence.entity.ProductEntity;
import com.sparta.logistics.product.infrastructure.persistence.repository.ProductCommandRepository;
import com.sparta.logistics.product.infrastructure.persistence.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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
        try {
            ProductEntity productEntity = findById(product.getId());
            return productEntity.updateFrom(product, updatedBy)
                .toDomain();
        } catch (OptimisticLockingFailureException e) {
            log.warn("상품 수정 실패 ={}", e);
            throw new ProductLogicException("다른 사용자가 상품 정보를 수정하고 있습니다. 다시 시도해주세요.");
        }
    }

    @Transactional
    @Override
    public Product changeProductQuantity(ProductForUpdateQuantity product, Long updatedBy) {
        try {
            ProductEntity productEntity = findById(product.getId());
            return productEntity.changeQuantity(product, updatedBy)
                .toDomain();
        } catch (OptimisticLockingFailureException e) {
            log.warn("상품 수정 실패 ={}", e);
            throw new ProductLogicException("다른 사용자가 상품 정보를 수정하고 있습니다. 다시 시도해주세요.");
        }
    }

    private ProductEntity findById(Long productId) {
        return productQueryRepository.findById(productId).orElseThrow(() ->
            new ProductLogicException("유효하지 않은 상품 정보"));
    }
}
