package com.sparta.logistics.hubcompany.infrastructure.persistence.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.CompanyEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.entity.QCompanyEntity;
import com.sparta.logistics.hubcompany.infrastructure.persistence.search.CompanySearchCondition;
import com.sparta.logistics.hubcompany.infrastructure.persistence.search.sort.CompanySort;
import com.sparta.logistics.hubcompany.infrastructure.persistence.search.sort.Sort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sparta.logistics.hubcompany.infrastructure.persistence.entity.QCompanyEntity.*;

@RequiredArgsConstructor
@Repository
public class CompanyQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public Page<CompanyEntity> findAll(CompanySearchCondition searchCondition) {

        BooleanExpression filterCondition = getFilterCondition(searchCondition.getKeyword());

        List<CompanyEntity> contents = queryFactory
                .selectFrom(QCompanyEntity.companyEntity)
                .where(QCompanyEntity.companyEntity.isDeleted.isFalse())
                .where(filterCondition)
                .orderBy(getOrderSpecifiers(searchCondition.getSort()).toArray(new OrderSpecifier[0]))
                .offset(searchCondition.getPage())
                .limit(searchCondition.getSize())
                .fetch();

        // 총 개수 조회
        Long total = Optional.ofNullable(
                queryFactory
                        .select(companyEntity.count())
                        .from(companyEntity)
                        .where(
                                companyEntity.isDeleted.isFalse(),
                                filterCondition
                        )
                        .fetchOne()
        ).orElse(0L);

        PageRequest pageRequest = PageRequest.of(searchCondition.getPage(), searchCondition.getSize());
        return new PageImpl<>(contents, pageRequest, total);
    }

    private BooleanExpression getFilterCondition(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return null;
        }
        return QCompanyEntity.companyEntity.name.containsIgnoreCase(keyword)
                .or(QCompanyEntity.companyEntity.address.containsIgnoreCase(keyword))
                .or(QCompanyEntity.companyEntity.type.containsIgnoreCase(keyword));
    }

    private List<OrderSpecifier<?>> getOrderSpecifiers(Sort sort) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        if (sort instanceof CompanySort companySort) {
            String sortType = companySort.getSortType();
            boolean isAsc = companySort.isAsc();

            if ("company_id".equalsIgnoreCase(sortType)) {
                orderSpecifiers.add(new OrderSpecifier<>(isAsc ? Order.ASC : Order.DESC, QCompanyEntity.companyEntity.companyId));
            } else if ("created_at".equalsIgnoreCase(sortType)) {
                orderSpecifiers.add(new OrderSpecifier<>(isAsc ? Order.ASC : Order.DESC, QCompanyEntity.companyEntity.createdAt));
            }
        }
        return orderSpecifiers;
    }
}
