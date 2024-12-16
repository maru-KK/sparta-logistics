package com.sparta.logistics.product.infrastructure.cache.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.domain.vo.Quantity;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductCacheAdapterTest {

    private static final Product TEST_PRODUCT = new Product(1L, 1L, 1L, Quantity.valueOf(1), "test");

    @Autowired
    private ProductCacheAdapter productCacheAdapter;

    @Test
    @DisplayName("캐시 생성 테스트")
    public void saveProductCache_successTest() {
        // Given
        Product save = productCacheAdapter.save(TEST_PRODUCT);
        // When
        Optional<Product> productOptional = productCacheAdapter.findOne(save);
        // Then
        Assertions.assertAll(
            () -> Assertions.assertTrue(productOptional.isPresent()),
            () -> Assertions.assertEquals(TEST_PRODUCT.getId(), productOptional.get().getId())
        );
    }
}