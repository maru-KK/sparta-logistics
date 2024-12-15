package com.sparta.logistics.delivery.infrastructure.persistence.search;

import com.sparta.logistics.delivery.domain.vo.DeliveryStatus;
import com.sparta.logistics.delivery.infrastructure.persistence.search.sort.DeliverySort;
import com.sparta.logistics.delivery.infrastructure.persistence.search.sort.Sort;
import lombok.*;

import java.util.List;
import java.util.Objects;

@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class DeliverySearchCondition {

    private static final int DEFAULT_PAGE = 0;

    private static final int DEFAULT_SIZE = 10;
    private static final List<Integer> AVAILABLE_SIZES = List.of(10, 30, 50);

    // pageable option
    private final int page;
    private final int size;
    private final Sort sort;

    // filter option
    private String  status;
    private String deliveryAddress;
    private String recipientName;
    private String recipientSnsId;
    private Long orderId;
    private Long originHubId;
    private Long destinationHubId;
    private final String keyword;

    public static DeliverySearchCondition of(
            String requestPage,
            String requestSize,
            String requestSort,
            String requestStatus,
            String requestDeliveryAddress,
            String requestRecipientName,
            String requestRecipientSnsId,
            String requestOrderId,
            String requestOriginHubId,
            String requestDestinationHubId,
            String requestKeyword) {

        return DeliverySearchCondition.builder()
                .page(parseIntPageOrDefault(requestPage))
                .size(parseIntSizeOrDefault(requestSize))
                .sort(DeliverySort.valueOf(requestSort))
                .status(parseDeliveryStatus(requestStatus))
                .deliveryAddress(requestDeliveryAddress)
                .recipientName(requestRecipientName)
                .recipientSnsId(requestRecipientSnsId)
                .orderId(parseLongOrNull(requestOrderId))
                .originHubId(parseLongOrNull(requestOriginHubId))
                .destinationHubId(parseLongOrNull(requestDestinationHubId))
                .keyword(requestKeyword)
                .build();
    }

    private static String parseDeliveryStatus(String status) {
        try {
            return status != null ? DeliveryStatus.valueOf(status.toUpperCase()).name() : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static int parseIntPageOrDefault(String requestPage) {
        try {
            return Integer.parseInt(requestPage);
        } catch (NumberFormatException exception) {
            return DEFAULT_PAGE;
        }
    }

    private static int parseIntSizeOrDefault(String requestSize) {
        try {
            int size = Integer.parseInt(requestSize);
            for (Integer availableSize : AVAILABLE_SIZES) {
                if (Objects.equals(availableSize, size)) {
                    return size;
                }
            }
            return DEFAULT_SIZE;

        } catch (NumberFormatException exception) {
            return DEFAULT_SIZE;
        }
    }

    private static Long parseLongOrNull(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}
