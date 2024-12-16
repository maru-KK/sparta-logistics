package com.sparta.logistics.delivery.infrastructure.persistence.search;

import com.sparta.logistics.delivery.domain.vo.DeliveryLogStatus;
import com.sparta.logistics.delivery.infrastructure.persistence.search.sort.DeliverySort;
import com.sparta.logistics.delivery.infrastructure.persistence.search.sort.Sort;
import lombok.*;

import java.util.List;
import java.util.Objects;

@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class DeliveryLogSearchCondition {

    private static final int DEFAULT_PAGE = 0;

    private static final int DEFAULT_SIZE = 10;
    private static final List<Integer> AVAILABLE_SIZES = List.of(10, 30, 50);

    // pageable option
    private final int page;
    private final int size;
    private final Sort sort;

    // filter option
    private Long deliveryId;
    private Long originHubId;
    private Long destinationHubId;
    private Long deliveryPersonId;
    private DeliveryLogStatus status;

    public static DeliveryLogSearchCondition of(
            String requestPage,
            String requestSize,
            String requestSort,
            String requestDeliveryId,
            String requestOriginHubId,
            String requestDestinationHubId,
            String requestDeliveryPersonId,
            String requestStatus
    ) {
        return DeliveryLogSearchCondition.builder()
                .page(parseIntPageOrDefault(requestPage))
                .size(parseIntSizeOrDefault(requestSize))
                .sort(DeliverySort.valueOf(requestSort))
                .deliveryId(parseLongOrNull(requestDeliveryId))
                .originHubId(parseLongOrNull(requestOriginHubId))
                .destinationHubId(parseLongOrNull(requestDestinationHubId))
                .deliveryPersonId(parseLongOrNull(requestDeliveryPersonId))
                .status(parseDeliveryLogStatus(requestStatus))
                .build();
    }

    private static DeliveryLogStatus parseDeliveryLogStatus(String status) {
        try {
            return status != null ? DeliveryLogStatus.valueOf(status.toUpperCase()) : null;
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
