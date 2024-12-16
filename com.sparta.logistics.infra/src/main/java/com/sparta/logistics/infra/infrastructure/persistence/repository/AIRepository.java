package com.sparta.logistics.infra.infrastructure.persistence.repository;

import com.sparta.logistics.infra.infrastructure.persistence.entity.AIEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AIRepository extends JpaRepository<AIEntity, Long> {
}
