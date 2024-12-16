package com.sparta.logistics.hubroute.infrastructure.cache.adapter;

import com.sparta.logistics.hubroute.domain.HubRoute;
import com.sparta.logistics.hubroute.infrastructure.cache.dto.HubRouteCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HubRouteCacheAdapter {

    private final ValueOperations<String, HubRouteCache> valueOperations;

    public HubRouteCacheAdapter(RedisTemplate<String, HubRouteCache> redisTemplate) {
        this.valueOperations = redisTemplate.opsForValue();
    }

    public void save(HubRoute hubRoute) {
        HubRouteCache hubRouteCache = HubRouteCache.from(hubRoute);
        valueOperations.set(hubRouteCache.key(), hubRouteCache);
    }

    public Optional<HubRoute> findById(Long id) {
        String key = HubRouteCache.findKeyFrom(id);
        return Optional.ofNullable(valueOperations.get(key))
                .map(HubRouteCache::toDomain);
    }

    public Optional<HubRoute> findByOriginAndDestination(Long originHubId, Long destinationHubId) {
        String key = HubRouteCache.findKeyFrom(originHubId, destinationHubId);
        return Optional.ofNullable(valueOperations.get(key))
                .map(HubRouteCache::toDomain);
    }
}