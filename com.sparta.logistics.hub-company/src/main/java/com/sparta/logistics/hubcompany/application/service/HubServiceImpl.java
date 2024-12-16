package com.sparta.logistics.hubcompany.application.service;

import com.sparta.logistics.hubcompany.application.dto.HubCreationRequestDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.domain.Hub;
import com.sparta.logistics.hubcompany.infrastructure.auth.AuthPort;
import com.sparta.logistics.hubcompany.infrastructure.auth.dto.UserDetailResponse;
import com.sparta.logistics.hubcompany.infrastructure.cache.adaptor.HubCacheAdapter;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.repository.HubRepository;
import com.sparta.logistics.hubcompany.presentation.exception.exceptions.InvalidAccessResourceException;
import com.sparta.logistics.hubcompany.presentation.exception.exceptions.ResourceNotFoundException;
import com.sparta.logistics.hubcompany.presentation.rest.dto.security.Actor;
import com.sparta.logistics.hubcompany.presentation.rest.dto.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HubServiceImpl implements HubService {

    private final HubRepository hubRepository;
    private final AuthPort authPort;

    @Override
    public HubEntity getHubById(Long hubId) {
        return hubRepository.findById(hubId)
                .orElseThrow(() -> new ResourceNotFoundException("허브를 찾을 수 없습니다: " + hubId));
    }

    @Override
    public List<HubResponseDto> getAllHubs() {
        List<HubEntity> hubs = hubRepository.findAll();
        return hubs.stream()
                .map(HubResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public HubResponseDto createHub(HubCreationRequestDto request, Actor actor) {
        UserDetailResponse user = authPort.findUser(request.getUserId());
        validateCreatePermission(actor);

        HubEntity hubEntity = HubEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .userId(request.getUserId())
                .build();

        HubEntity savedHub = hubRepository.save(hubEntity);

        return new HubResponseDto(savedHub);
    }

    private void validateCreatePermission(Actor actor) {
        if (actor.role() == Role.MASTER) {
            return;
        } else {
            throw new InvalidAccessResourceException("허브 생성 권한이 없습니다.");
        }
    }

}