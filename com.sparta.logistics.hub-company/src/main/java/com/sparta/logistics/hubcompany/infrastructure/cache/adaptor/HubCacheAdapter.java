package com.sparta.logistics.hubcompany.infrastructure.cache.adaptor;

import com.sparta.logistics.hubcompany.domain.Hub;
import com.sparta.logistics.hubcompany.infrastructure.cache.dto.HubCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HubCacheAdapter {

    private final ValueOperations<String, HubCache> valueOperations;

    public HubCacheAdapter(RedisTemplate<String, HubCache> redisTemplate) {
        this.valueOperations = redisTemplate.opsForValue();
    }

    public Hub save(Hub hub) {
        HubCache hubCache = HubCache.from(hub);
        valueOperations.set(hubCache.key(), hubCache);
        return hubCache.toDomain();
    }

    public Optional<Hub> findOne(Hub hub) {
        HubCache hubCache = HubCache.from(hub);
        return Optional.ofNullable(valueOperations.get(hubCache.key()))
                .map(HubCache::toDomain);
    }

    public Optional<Hub> findById(Long id) {
        String key = HubCache.findKeyFrom(id);
        return Optional.ofNullable(valueOperations.get(key))
                .map(HubCache::toDomain);
    }
}