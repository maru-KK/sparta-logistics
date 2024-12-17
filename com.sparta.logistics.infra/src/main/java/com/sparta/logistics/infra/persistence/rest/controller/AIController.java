package com.sparta.logistics.infra.persistence.rest.controller;

import com.sparta.logistics.infra.application.service.AIService;
import com.sparta.logistics.infra.infrastructure.persistence.entity.AIEntity;
import com.sparta.logistics.infra.persistence.rest.dto.InfraRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/infra/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

//    @PostMapping
//    public ResponseEntity<AIEntity> generateResponse(@RequestBody InfraRequestDto infraRequestDto) {
//        AIEntity aiEntity = aiService.generateResponse(infraRequestDto.toDomain());
//        return ResponseEntity.ok(aiEntity);
//    }
}
