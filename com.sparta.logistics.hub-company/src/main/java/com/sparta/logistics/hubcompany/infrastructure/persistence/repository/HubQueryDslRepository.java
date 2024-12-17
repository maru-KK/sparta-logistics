package com.sparta.logistics.hubcompany.infrastructure.persistence.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.HubEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.search.HubSearchCondition;
import com.sparta.logistics.hubcompany.infrastructure.persistence.search.sort.HubSort;
import com.sparta.logistics.hubcompany.infrastructure.persistence.search.sort.Sort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.sparta.logistics.hubcompany.infrastructure.persistence.entity.QHubEntity.*;

@RequiredArgsConstructor
@Repository
public class HubQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public Page<HubEntity> findAll(HubSearchCondition searchCondition) {
        // 검색 조건과 페이지 정보 생성
        BooleanExpression filterCondition = getFilterCondition(searchCondition.getKeyword());

        // 페이지네이션 설정
        int page = searchCondition.getPage();
        int size = searchCondition.getSize();
        PageRequest pageRequest = PageRequest.of(page, size);

        // 정렬 조건
        List<HubEntity> contents = queryFactory
                .selectFrom(hubEntity)
                .where(
                        hubEntity.isDeleted.isFalse(),
                        filterCondition
                )
                .orderBy(getHubSpecifier(searchCondition.getSort()))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        // 총 개수 조회
        Long total = Optional.ofNullable(
                queryFactory
                        .select(hubEntity.count())
                        .from(hubEntity)
                        .where(
                                hubEntity.isDeleted.isFalse(),
                                filterCondition
                        )
                        .fetchOne()
        ).orElse(0L);


        return new PageImpl<>(contents, pageRequest, total);
    }

    private BooleanExpression getFilterCondition(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return null; // 검색어가 없으면 조건 없음
        }
        return hubEntity.name.containsIgnoreCase(keyword)
                .or(hubEntity.address.containsIgnoreCase(keyword));
    }

    private com.querydsl.core.types.OrderSpecifier<?> getHubSpecifier(Sort sort) {
        if (sort instanceof HubSort hubSort) {
            String sortType = hubSort.getSortType();
            boolean isAsc = hubSort.isAsc();

            if ("hub_id".equalsIgnoreCase(sortType)) {
                return isAsc ? hubEntity.hubId.asc() : hubEntity.hubId.desc();
            } else if ("created_at".equalsIgnoreCase(sortType)) {
                return isAsc ? hubEntity.createdAt.asc() : hubEntity.createdAt.desc();
            }
        }
        return hubEntity.createdAt.desc();
    }
}