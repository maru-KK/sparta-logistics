package com.sparta.logistics.product.infrastructure.cache.adapter;

import com.sparta.logistics.product.domain.Product;
import com.sparta.logistics.product.infrastructure.cache.dto.ProductCache;
import java.util.Optional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class ProductCacheAdapter {

    private final ValueOperations<String, ProductCache> productOperations;

    public ProductCacheAdapter(RedisTemplate<String, ProductCache> productRedisTemplate) {
        productOperations = productRedisTemplate.opsForValue();
    }

    public Product save(Product product) {
        ProductCache productCache = ProductCache.from(product);
        productOperations.set(productCache.key(), productCache);
        return productCache.toDomain();
    }

    public Optional<Product> findOne(Product product) {
        ProductCache productCache = ProductCache.from(product);
        return Optional.ofNullable(productOperations.get(productCache.key()))
            .map(ProductCache::toDomain)
            .or(Optional::empty);
    }

    public Optional<Product> findOne(Long productId) {
        String key = ProductCache.findKeyFrom(productId);
        return Optional.ofNullable(productOperations.get(key))
            .map(ProductCache::toDomain)
            .or(Optional::empty);
    }
}
