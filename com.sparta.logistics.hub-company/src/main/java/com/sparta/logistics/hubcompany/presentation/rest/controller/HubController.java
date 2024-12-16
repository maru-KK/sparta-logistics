package com.sparta.logistics.hubcompany.presentation.rest.controller;

import com.sparta.logistics.hubcompany.application.dto.HubCreationRequestDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.application.service.HubService;
import com.sparta.logistics.hubcompany.domain.Hub;
import com.sparta.logistics.hubcompany.infrastructure.cache.adaptor.HubCacheAdapter;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hubs")
public class HubController {

    private final HubService hubService;
    private final HubCacheAdapter hubCacheAdapter;

    @GetMapping("/{hubId}")
    public ResponseEntity<HubEntity> getHubById(@PathVariable("hubId") Long hubId) {
        Optional<Hub> cachedHub = hubCacheAdapter.findById(hubId);
        if (cachedHub.isPresent()) {
            return ResponseEntity.ok(cachedHub.get().toEntity());
        }
        HubEntity hub = hubService.getHubById(hubId);
        hubCacheAdapter.save(hub.toDomain());
        return ResponseEntity.ok(hub);
    }

    @GetMapping
    public ResponseEntity<List<HubResponseDto>> getAllHubs() {
        List<HubResponseDto> hubs = hubService.getAllHubs();
        return ResponseEntity.ok(hubs);
    }

    @PostMapping
    public ResponseEntity<String> createHub(@RequestBody HubCreationRequestDto request,
                                            @RequestHeader("X-User-Id") Long userId,
                                            @RequestHeader("X-Role") String role) {
        if (!"HUB".equals(role) && !"MASTER".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("허브 생성 권한이 없습니다.");
        }

        HubResponseDto hubResponse = hubService.createHub(request, userId);
        Long hubId = hubResponse.getHubId();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Hub created with ID: " + hubId);
    }
}
