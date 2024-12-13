package com.sparta.logistics.order.infrastructure.persistence.search;



import com.sparta.logistics.order.infrastructure.persistence.entity.vo.OrderEntityStatus;
import com.sparta.logistics.order.infrastructure.persistence.search.sort.OrderSort;
import com.sparta.logistics.order.infrastructure.persistence.search.sort.Sort;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderSearchCondition {

    private static final int DEFAULT_PAGE = 0;

    private static final int DEFAULT_SIZE = 10;
    private static final List<Integer> AVAILABLE_SIZES = List.of(10, 30, 50);

    // pageable option
    private final int page;
    private final int size;
    private final Sort sort;

    // filter option
    private final Long supplyCompanyId;
    private final Long productId;
    private final OrderEntityStatus status;

    public static OrderSearchCondition of(
        String requestPage, String requestSize, String requestSort,
        String requestSupplyCompanyId, String requestProductId,
        String requestStatus
    ) {
        int page = parseIntPageOrDefault(requestPage);
        int size = parseIntSizeOrDefault(requestSize);
        Sort sort = OrderSort.valueOf(requestSort);

        Long supplyCompanyId = parseLongOrNull(requestSupplyCompanyId);
        Long consumeCompanyId = parseLongOrNull(requestProductId);
        OrderEntityStatus status = OrderEntityStatus.of(requestStatus);

        return new OrderSearchCondition(
            page, size, sort,
            supplyCompanyId, consumeCompanyId, status
        );
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
