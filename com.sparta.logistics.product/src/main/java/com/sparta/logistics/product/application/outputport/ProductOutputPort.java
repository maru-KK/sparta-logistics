package com.sparta.logistics.product.application.outputport;

import com.sparta.logistics.product.domain.Company;
import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.vo.ProductForCreate;
import com.sparta.logistics.product.domain.vo.ProductForUpdateQuantity;

public interface ProductOutputPort {

    Product saveOne(ProductForCreate product, Company company);

    Product update(Product product, Long updatedBy);

    Product changeProductQuantity(ProductForUpdateQuantity productForUpdate, Long userId);
}
