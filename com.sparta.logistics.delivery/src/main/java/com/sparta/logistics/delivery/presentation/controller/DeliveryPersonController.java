package com.sparta.logistics.delivery.presentation.controller;

import com.sparta.logistics.delivery.application.dto.DeliveryPersonResponse;
import com.sparta.logistics.delivery.application.service.DeliveryPersonService;
import com.sparta.logistics.delivery.presentation.dto.DeliveryPersonCreateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery-person")
public class DeliveryPersonController {
    private final DeliveryPersonService deliveryPersonService;

    @PostMapping()
    public ResponseEntity<?> createDeliveryPerson(@Valid @RequestBody DeliveryPersonCreateRequestDto requestDto) {
        DeliveryPersonResponse deliveryPersonResponse = deliveryPersonService.createDeliveryPerson(requestDto);

        return ResponseEntity.ok(deliveryPersonResponse);
    }
}
