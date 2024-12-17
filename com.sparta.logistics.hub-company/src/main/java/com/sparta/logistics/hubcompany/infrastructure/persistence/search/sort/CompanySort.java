package com.sparta.logistics.hubcompany.infrastructure.persistence.search.sort;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanySort implements Sort {

    private static final String DEFAULT_SORT_TYPE = "created_at";
    private static final List<String> AVAILABLE_SORTS = List.of("company_id", "created_at");
    private static final String SEPARATOR = ".";

    public static final CompanySort DEFAULT_SORT = new CompanySort(DEFAULT_SORT_TYPE, true);

    private String sortType;
    private boolean isAsc;

    @Override
    public String getSortType() {
        return sortType;
    }

    @Override
    public boolean isAsc() {
        return isAsc;
    }

    public static CompanySort valueOf(String requestSort) {
        if (requestSort == null || requestSort.isBlank()) {
            return DEFAULT_SORT;
        }

        String sort = requestSort;
        boolean isAsc = true;

        if (requestSort.contains(SEPARATOR)) {
            String[] sortAndAsc = requestSort.split("\\.");
            sort = sortAndAsc[0];
            isAsc = !Objects.equals(sortAndAsc[1], "DESC");
        }

        sort = validateSortAndGetOrDefault(sort);
        return new CompanySort(sort, isAsc);
    }

    private static String validateSortAndGetOrDefault(String sort) {
        for (String availableSort : AVAILABLE_SORTS) {
            if (availableSort.equalsIgnoreCase(sort)) {
                return availableSort;
            }
        }
        return DEFAULT_SORT_TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompanySort that = (CompanySort) o;
        return isAsc == that.isAsc && Objects.equals(sortType, that.sortType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortType, isAsc);
    }
}
