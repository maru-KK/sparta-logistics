package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.HubCreationRequestDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.presentation.rest.dto.security.Actor;

import java.util.List;

public interface HubService {
    HubEntity getHubById(Long hubId);
    List<HubResponseDto> getAllHubs();
    HubResponseDto createHub(HubCreationRequestDto request, Actor actor);
}
