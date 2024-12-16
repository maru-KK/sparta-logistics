package com.sparta.logistics.delivery.presentation.controller;

import com.sparta.logistics.delivery.application.service.DeliveryLogService;
import com.sparta.logistics.delivery.domain.DeliveryLog;
import com.sparta.logistics.delivery.infrastructure.persistence.search.DeliveryLogSearchCondition;
import com.sparta.logistics.delivery.presentation.dto.DeliveryLogResponse;
import com.sparta.logistics.delivery.presentation.util.search.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery-log")
public class DeliveryLogController {
    private final DeliveryLogService deliveryLogService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeliveryLog(@PathVariable Long id) {
        DeliveryLog deliveryLog = deliveryLogService.findOne(id);

        return ResponseEntity.ok(DeliveryLogResponse.from(deliveryLog));
    }

    @GetMapping()
    public ResponseEntity<?> getDeliveryLogList(@SearchCondition DeliveryLogSearchCondition searchCondition) {
        Page<DeliveryLogResponse> responsePage = deliveryLogService.search(searchCondition).map(DeliveryLogResponse::from);

        return ResponseEntity.ok(responsePage);
    }
}
