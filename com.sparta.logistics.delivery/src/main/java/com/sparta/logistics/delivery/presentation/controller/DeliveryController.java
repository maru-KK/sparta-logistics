package com.sparta.logistics.delivery.presentation.controller;

import com.sparta.logistics.delivery.application.dto.DeliveryCreateRequestDto;
import com.sparta.logistics.delivery.application.service.DeliveryService;
import com.sparta.logistics.delivery.domain.Delivery;
import com.sparta.logistics.delivery.presentation.dto.DeliveryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping()
    public ResponseEntity<?> createDelivery(@RequestBody DeliveryCreateRequestDto requestDto) {
        DeliveryResponseDto responseDto = DeliveryResponseDto.from(deliveryService.createDelivery(requestDto));

        return ResponseEntity.ok(responseDto);
    }
}
