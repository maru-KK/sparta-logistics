package com.sparta.logistics.delivery.presentation.dto;


import com.sparta.logistics.delivery.domain.vo.DeliveryPersonStatus;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonType;
import jakarta.validation.constraints.NotNull;

public record DeliveryPersonCreateRequestDto(
        @NotNull(message = "userId는 null일 수 없습니다.")
        Long userId,
        Long hubId,
        @NotNull(message = "type은 필수 입력값입니다.")
        DeliveryPersonType type,
        @NotNull(message = "status는 필수 입력값입니다.")
        DeliveryPersonStatus status

) {

}
