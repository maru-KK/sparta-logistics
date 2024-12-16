package com.sparta.logistics.order.application.service;

import com.sparta.logistics.order.application.outputport.ProductOutputPort;
import com.sparta.logistics.order.domain.order.Product;
import com.sparta.logistics.order.domain.order.vo.ProductForChangeQuantity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductOutputPort productOutputPort;

    public Product getOrderableProduct(Long productId, Integer requireQuantity) {
        Product product = productOutputPort.findOne(productId);
        validateProduct(requireQuantity, product);
        return product;
    }

    private static void validateProduct(Integer requireQuantity, Product product) {
        if (product.equals(Product.ERROR_PRODUCT)) {
            throw new IllegalArgumentException("주문하신 상품에 대한 정보가 존재하지 않습니다.");
        }

        if (!product.isEnoughQuantity(requireQuantity)) {
            throw new IllegalArgumentException("주문하신 상품의 재고가 충분하지 않습니다.");
        }
    }


    public void decreaseProductQuantity(Long productId, Integer quantity, Long orderedBy) {
        ProductForChangeQuantity productChange =
            new ProductForChangeQuantity(productId, quantity, true, orderedBy);

        productOutputPort.changeProductQuantity(productChange);
    }

    public void increaseProductQuantity(Long productId, Integer quantity, Long orderedBy) {
        ProductForChangeQuantity productChange =
            new ProductForChangeQuantity(productId, quantity, false, orderedBy);

        productOutputPort.changeProductQuantity(productChange);
    }
}
