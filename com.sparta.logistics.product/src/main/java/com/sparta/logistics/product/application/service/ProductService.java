package com.sparta.logistics.product.application.service;

import com.sparta.logistics.product.application.outputport.HubOutputPort;
import com.sparta.logistics.product.application.outputport.ProductOutputPort;
import com.sparta.logistics.product.domain.Company;
import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.ProductForCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final HubOutputPort hubOutputPort;
    private final ProductOutputPort productOutputPort;

    public Product createProduct(ProductForCreate product) {
        Company company = validateAndGetCompany(product);
        return productOutputPort.saveOne(product, company);
    }

    private Company validateAndGetCompany(ProductForCreate product) {
        Company company = hubOutputPort.findCompany(product.getCreatedBy())
            .orElseThrow(() -> new IllegalArgumentException("상품 생성 실패: 권한 인증 실패, 잠시 후 다시 시도해 주세요"));

        if (company.isConsumer()) {
            throw new IllegalArgumentException("상품 생성 실패: 권한 없음(생산업체만 상품 등록 가능)");
        }
        return company;
    }
}
