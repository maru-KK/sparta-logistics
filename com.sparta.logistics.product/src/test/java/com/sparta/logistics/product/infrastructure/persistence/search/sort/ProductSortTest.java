package com.sparta.logistics.product.infrastructure.persistence.search.sort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductSortTest {

    @Test
    @DisplayName("[Sort 객체 생성 성공 테스트] 빈 문자열이 주어진 경우 기본 정렬 전략을 생성한다.")
    public void sortCreateSuccessTest_GivenEmptyRequest() {
        // Given
        String requestSort = "";
        // When
        ProductSort sort = ProductSort.valueOf(requestSort);
        // Then
        Assertions.assertAll(
            () -> assertEquals(ProductSort.DEFAULT_SORT, sort),
            () -> assertEquals("created_at", sort.getSortType()),
            () -> assertTrue(sort.isAsc())
        );
    }

    @Test
    @DisplayName("[Sort 객체 생성 성공 테스트] null이 주어진 경우 기본 정렬 전략을 생성한다.")
    public void sortCreateSuccessTest_GivenNull() {
        // Given
        String requestSort = null;
        // When
        ProductSort sort = ProductSort.valueOf(requestSort);
        // Then
        Assertions.assertAll(
            () -> assertEquals(ProductSort.DEFAULT_SORT, sort),
            () -> assertEquals("created_at", sort.getSortType()),
            () -> assertTrue(sort.isAsc())
        );
    }

    @Test
    @DisplayName("[Sort 객체 생성 성공 테스트] 유효하지 않은 정렬 타입이 주어진 경우 기본 정렬타입으로 생성한다.")
    public void sortCreateSuccessTest_GivenInvalidSortType() {
        // Given
        String requestSort = "invalidType.DESC";
        // When
        ProductSort sort = ProductSort.valueOf(requestSort);
        // Then
        Assertions.assertAll(
            () -> assertEquals("created_at", sort.getSortType()),
            () -> assertFalse(sort.isAsc())
        );
    }

    @Test
    @DisplayName("[Sort 객체 생성 성공 테스트] 유효하지 않은 정렬순서가 주어진 경우 기본 정렬순서로 생성한다.")
    public void sortCreateSuccessTest_GivenInvalidAscending() {
        // Given
        String requestSort = "created_at.invalidAscending";
        // When
        ProductSort sort = ProductSort.valueOf(requestSort);
        // Then
        Assertions.assertAll(
            () -> assertEquals("created_at", sort.getSortType()),
            () -> assertTrue(sort.isAsc())
        );
    }
}