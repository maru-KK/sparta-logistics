package com.sparta.logistics.delivery.presentation.dto;


import com.sparta.logistics.delivery.domain.vo.DeliveryPersonType;
import jakarta.validation.constraints.NotNull;

public record DeliveryPersonCreateRequestDto(
        @NotNull(message = "userId는 null일 수 없습니다.")
        Long userId,
        @NotNull(message = "hubId는 null일 수 없습니다.")
        Long hubId,
        @NotNull(message = "type은 필수 입력값입니다.")
        DeliveryPersonType type,
        @NotNull(message = "sequence는 null일 수 없습니다.")
        Integer sequence
) {

}
