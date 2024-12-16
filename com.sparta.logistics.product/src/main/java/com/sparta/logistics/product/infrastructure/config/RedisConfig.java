package com.sparta.logistics.product.infrastructure.config;

import com.sparta.logistics.product.infrastructure.cache.dto.ProductCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, ProductCache> itemRedisTemplate(
        RedisConnectionFactory connectionFactory
    ) {
        RedisTemplate<String, ProductCache> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.json());
        return template;
    }
}
