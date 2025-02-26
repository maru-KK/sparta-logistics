package com.sparta.logistics.infra.application.util;

import com.sparta.logistics.infra.domain.ai.Ai;
import com.sparta.logistics.infra.persistence.rest.dto.InfraRequestDto;
import lombok.RequiredArgsConstructor;

public class AIUtil {

    public static String createSlackMessage(Ai ai, String aiResponse) {
        try {
            return String.format(
                    "주문 번호: %s\n" +
                            "주문자 정보: %s\n" +
                            "상품 정보: %s\n" +
                            "요청 사항: %s %s\n" +
                            "발송지: %s\n" +
                            "경유지: %s\n" +
                            "도착지: %s\n" +
                            "배송 담당자: %s / %s\n\n" +
                            "위 내용을 바탕으로 도출된 최종 발송 시한은 %s 입니다.",
                    ai.getOrderId(), ai.getUserName(),
                    ai.getProductName(), ai.getProductQuantity(),
                    ai.getRequest(),
                    ai.getOriginHub(), ai.getDestinationHub(),
                    ai.getCompanyAddress(),
                    ai.getDeliveryPersonName(), ai.getSnsAccount(),
                    aiResponse
            );
        } catch (Exception e) {
            e.printStackTrace();
            return "AI 응답 처리 실패";
        }
    }
}
