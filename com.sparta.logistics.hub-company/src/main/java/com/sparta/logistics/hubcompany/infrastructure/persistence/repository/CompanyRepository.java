package com.sparta.logistics.hubcompany.infrastructure.persistence.repository;

import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}