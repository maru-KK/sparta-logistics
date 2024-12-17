package com.sparta.logistics.hubcompany.infrastructure.persistence.search;


import com.sparta.logistics.hubcompany.infrastructure.persistence.search.sort.HubSort;
import com.sparta.logistics.hubcompany.infrastructure.persistence.search.sort.Sort;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CompanySearchCondition {

    private static final int DEFAULT_PAGE = 0;

    private static final int DEFAULT_SIZE = 10;
    private static final List<Integer> AVAILABLE_SIZES = List.of(10, 30, 50);

    // pageable option
    private final int page;
    private final int size;
    private final Sort sort;

    // filter option
    private final Long hubId;
    private final Long companyId;
    private final String keyword;

    public static CompanySearchCondition of(
        String requestPage, String requestSize, String requestSort,
        String requestHubId, String requestCompanyId, String requestKeyword
    ) {
        int page = parseIntPageOrDefault(requestPage);
        int size = parseIntSizeOrDefault(requestSize);
        Sort sort = HubSort.valueOf(requestSort);
        Long hubId = parseLongOrNull(requestHubId);
        Long companyId = parseLongOrNull(requestCompanyId);

        return new CompanySearchCondition(page, size, sort, hubId, companyId, requestKeyword);
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
