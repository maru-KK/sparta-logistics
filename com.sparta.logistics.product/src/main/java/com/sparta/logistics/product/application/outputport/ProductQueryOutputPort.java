package com.sparta.logistics.product.application.outputport;

import com.sparta.logistics.product.domain.Product;
import java.util.Optional;

public interface ProductQueryOutputPort {

    Optional<Product> findById(Long productId);
}
