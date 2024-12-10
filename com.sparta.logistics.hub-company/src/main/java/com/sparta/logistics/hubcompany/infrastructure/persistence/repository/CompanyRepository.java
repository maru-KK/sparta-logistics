package com.sparta.logistics.hubcompany.infrastructure.persistence.repository;

import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
