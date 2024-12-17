package com.sparta.logistics.hubcompany.infrastructure.persistence.adaptor;

import com.sparta.logistics.hubcompany.domain.Company;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.repository.CompanyQueryDslRepository;
import com.sparta.logistics.hubcompany.infrastructure.persistence.search.CompanySearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CompanyQueryAdaptor {

    private final CompanyQueryDslRepository companyQueryDslRepository;

    public Page<Company> search(CompanySearchCondition searchCondition) {
        return companyQueryDslRepository.findAll(searchCondition)
                .map(CompanyEntity::toDomain);
    }
}
