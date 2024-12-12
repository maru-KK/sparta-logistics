package com.sparta.logistics.product.infrastructure.persistence.search.sort;

import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductSort implements Sort {

    private static final String DEFAULT_SORT_TYPE = "created_at";
    private static final List<String> AVAILABLE_SORTS = List.of("created_at", "name");
    private static final String SEPARATER = ".";

    public static final ProductSort DEFAULT_SORT = new ProductSort(DEFAULT_SORT_TYPE, true);

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

    public static ProductSort valueOf(String requestSort) {
        if (requestSort == null || requestSort.isBlank()) {
            return DEFAULT_SORT;
        }

        String sort = requestSort;
        boolean isAsc = true;

        if (requestSort.contains(SEPARATER)) {
            String[] sortAndAsc = requestSort.split("\\.");
            sort = sortAndAsc[0];
            isAsc = !Objects.equals(sortAndAsc[1], "DESC");
        }

        sort = validateSortAndGetOrDefault(sort);
        return new ProductSort(sort, isAsc);
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
        ProductSort that = (ProductSort) o;
        return isAsc == that.isAsc && Objects.equals(sortType, that.sortType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortType, isAsc);
    }
}
