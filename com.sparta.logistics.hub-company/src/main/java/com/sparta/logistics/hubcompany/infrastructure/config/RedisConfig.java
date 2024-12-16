package com.sparta.logistics.hubcompany.infrastructure.config;

import com.sparta.logistics.hubcompany.infrastructure.cache.dto.HubCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, HubCache> hubRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, HubCache> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(RedisSerializer.json());
        template.setKeySerializer(RedisSerializer.string());
        return template;
    }
}
