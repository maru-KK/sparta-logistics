package com.sparta.logistics.hubcompany.presentation.rest.controller;

import com.sparta.logistics.hubcompany.application.dto.HubCreationRequestDto;
import com.sparta.logistics.hubcompany.application.dto.HubResponseDto;
import com.sparta.logistics.hubcompany.application.service.HubService;
import com.sparta.logistics.hubcompany.domain.Hub;
import com.sparta.logistics.hubcompany.infrastructure.cache.adaptor.HubCacheAdapter;
import com.sparta.logistics.hubcompany.infrastructure.persistence.adaptor.HubQueryAdaptor;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.search.HubSearchCondition;
import com.sparta.logistics.hubcompany.presentation.rest.dto.security.Actor;
import com.sparta.logistics.hubcompany.presentation.util.actor.LoginActor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final HubQueryAdaptor hubQueryAdaptor;

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
        HubResponseDto hubResponse = hubService.createHub(request);
        Long hubId = hubResponse.getHubId();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Hub created with ID: " + hubId);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Hub>> search(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "created_at") String sort,
            @RequestParam(value = "filter", required = false) String keyword
    ) {
        HubSearchCondition searchCondition = HubSearchCondition.of(
                String.valueOf(page),
                String.valueOf(size),
                sort,
                keyword
        );

        Page<Hub> result = hubQueryAdaptor.search(searchCondition);

        return ResponseEntity.ok(result);
    }
}
