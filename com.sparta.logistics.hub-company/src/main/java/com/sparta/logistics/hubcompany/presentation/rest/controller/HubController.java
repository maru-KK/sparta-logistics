package com.sparta.logistics.hubcompany.presentation.rest.controller;

import com.sparta.logistics.hubcompany.application.dto.HubCreationRequestDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.application.service.HubService;
import com.sparta.logistics.hubcompany.domain.Hub;
import com.sparta.logistics.hubcompany.infrastructure.cache.adaptor.HubCacheAdapter;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.presentation.rest.dto.security.Actor;
import com.sparta.logistics.hubcompany.presentation.util.actor.LoginActor;
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
                                            @LoginActor Actor actor) {
        HubResponseDto hubResponse = hubService.createHub(request, actor);
        Long hubId = hubResponse.getHubId();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Hub created with ID: " + hubId);
    }
}
