package com.sparta.logistics.delivery.presentation.controller;

import com.sparta.logistics.delivery.application.dto.DeliveryPersonResponse;
import com.sparta.logistics.delivery.application.service.DeliveryPersonService;
import com.sparta.logistics.delivery.presentation.dto.DeliveryPersonCreateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery-person")
public class DeliveryPersonController {
    private final DeliveryPersonService deliveryPersonService;

    @PostMapping()
    public ResponseEntity<?> createDeliveryPerson(@Valid @RequestBody DeliveryPersonCreateRequestDto requestDto) {
        DeliveryPersonResponse deliveryPersonResponse = deliveryPersonService.createDeliveryPerson(requestDto);

        return ResponseEntity.ok(deliveryPersonResponse);
    }

    @GetMapping("/{deliveryPersonId}")
    public ResponseEntity<?> getDeliveryPerson(@PathVariable Long deliveryPersonId) {
        DeliveryPersonResponse deliveryPersonResponse = deliveryPersonService.getDeliveryPerson(deliveryPersonId);

        return ResponseEntity.ok(deliveryPersonResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getDeliveryPersonList(@PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DeliveryPersonResponse> deliveryPersonResponse = deliveryPersonService.getDeliveryPersonList(pageable);

        return ResponseEntity.ok(deliveryPersonResponse);
    }
}
