package com.sparta.logistics.product.application.service;

import com.sparta.logistics.product.application.exception.ProductCreateFailureException;
import com.sparta.logistics.product.application.exception.ProductLogicException;
import com.sparta.logistics.product.application.exception.ProductUpdateFailureException;
import com.sparta.logistics.product.application.outputport.ProductOutputPort;
import com.sparta.logistics.product.application.outputport.ProductQueryOutputPort;
import com.sparta.logistics.product.domain.Company;
import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.ProductForCreate;
import com.sparta.logistics.product.domain.ProductForUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductOutputPort productOutputPort;
    private final ProductQueryOutputPort productQueryOutputPort;
    private final CompanyService companyService;

    public Product createProduct(ProductForCreate product) {
        try {
            Company company = companyService.validateAndGetSupplierCompany(product);
            return productOutputPort.saveOne(product, company);

        } catch (ProductLogicException e) {
            throw new ProductCreateFailureException(e.getMessage());
        }
    }

    public Product updateProduct(ProductForUpdate productForUpdate, Long updatedBy) {
        try {
            Product product = productQueryOutputPort.findById(productForUpdate.getId())
                .orElseThrow(() -> new ProductLogicException("유효하지 않은 상품 식별자"));

            product = product.updateFrom(productForUpdate);
            return productOutputPort.update(product, updatedBy);

        } catch (ProductLogicException e) {
            throw new ProductUpdateFailureException(e.getMessage());
        }
    }
}
