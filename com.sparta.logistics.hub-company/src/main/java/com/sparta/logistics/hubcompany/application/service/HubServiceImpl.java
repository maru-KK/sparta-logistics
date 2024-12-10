package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.HubCreationRequestDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.repository.HubRepository;
import com.sparta.logistics.hubcompany.presentation.exception.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HubServiceImpl implements HubService {

    private final HubRepository hubRepository;

    @Override
    public HubResponseDto getHubById(Long hubId) {
        HubEntity hub = hubRepository.findById(hubId)
                .orElseThrow(() -> new ResourceNotFoundException("허브를 찾을 수 없습니다: " + hubId));
        return new HubResponseDto(hub);
    }

    @Override
    public List<HubResponseDto> getAllHubs() {
        List<HubEntity> hubs = hubRepository.findAll();
        return hubs.stream()
                .map(HubResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public HubResponseDto createHub(HubCreationRequestDto request, Long userId) {
        HubEntity hubEntity = HubEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .userId(userId)
                .build();

        HubEntity savedHub = hubRepository.save(hubEntity);

        return new HubResponseDto(savedHub);
    }
}