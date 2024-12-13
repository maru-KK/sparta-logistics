package com.sparta.logistics.order.application.outputport;

import com.sparta.logistics.order.domain.order.Product;
import com.sparta.logistics.order.domain.order.vo.ProductForChangeQuantity;
import java.util.Optional;

public interface ProductOutputPort {

    Product findOne(Long productId);

    void changeProductQuantity(ProductForChangeQuantity productChange);
}
