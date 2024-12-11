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
import com.sparta.logistics.product.domain.exception.DomainException;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductOutputPort productOutputPort;
    private final ProductQueryOutputPort productQueryOutputPort;
    private final CompanyService companyService;

    /**
     * @Param: action - 비즈니스 로직
     * @Param: exceptionSupplier - 예외 발생 시 덮어씌워 던질 예외의 생성자
     * */
    private <T> T handleException(Supplier<T> action, Function<String, RuntimeException> exceptionSupplier) {
        try {
            return action.get();
        } catch (ProductLogicException | DomainException e) {
            throw exceptionSupplier.apply(e.getMessage());
        }
    }

    public Product createProduct(ProductForCreate product) {
        return handleException(() -> {
            Company company = companyService.validateAndGetSupplierCompany(product.getCreatedBy());
            return productOutputPort.saveOne(product, company);

        }, ProductCreateFailureException::new);
    }

    public Product updateProduct(ProductForUpdate productForUpdate, Long updatedBy) {
        return handleException(() -> {
            Company company = companyService.validateAndGetSupplierCompany(updatedBy);
            Product product = productQueryOutputPort.findById(productForUpdate.getId())
                .orElseThrow(() -> new ProductLogicException("유효하지 않은 상품 식별자"));

            product = product.updateFrom(productForUpdate, company);
            return productOutputPort.update(product, updatedBy);

        }, ProductUpdateFailureException::new);
    }
}
