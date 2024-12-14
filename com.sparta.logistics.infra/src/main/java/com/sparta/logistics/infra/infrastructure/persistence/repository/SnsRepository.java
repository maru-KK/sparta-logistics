package com.sparta.logistics.infra.infrastructure.persistence.repository;

import com.sparta.logistics.infra.infrastructure.persistence.entity.SnsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsRepository extends JpaRepository<SnsEntity, Long> {

}
