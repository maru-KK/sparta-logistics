package com.sparta.logistics.hubcompany.infrastructure.persistence.adaptor;

import com.sparta.logistics.hubcompany.domain.Hub;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.repository.HubQueryDslRepository;
import com.sparta.logistics.hubcompany.infrastructure.persistence.search.HubSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HubQueryAdaptor {

    private final HubQueryDslRepository hubQueryDslRepository;

    public Page<Hub> search(HubSearchCondition searchCondition) {
        return hubQueryDslRepository.findAll(searchCondition)
                .map(HubEntity::toDomain);
    }
}
