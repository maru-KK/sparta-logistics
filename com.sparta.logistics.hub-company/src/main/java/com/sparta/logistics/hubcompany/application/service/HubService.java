package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.HubCreationRequestDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;

import java.util.List;

public interface HubService {
    HubResponseDto getHubById(Long hubId);
    List<HubResponseDto> getAllHubs();
    HubResponseDto createHub(HubCreationRequestDto request, Long userId);
}
