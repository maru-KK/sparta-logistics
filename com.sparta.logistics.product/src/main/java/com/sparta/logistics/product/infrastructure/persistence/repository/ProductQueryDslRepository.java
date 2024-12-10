package com.sparta.logistics.product.infrastructure.persistence.repository;

import static com.sparta.logistics.product.infrastructure.persistence.entity.QProductEntity.*;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.product.infrastructure.persistence.entity.ProductEntity;
import com.sparta.logistics.product.infrastructure.persistence.search.ProductSearchCondition;
import com.sparta.logistics.product.infrastructure.persistence.search.sort.ProductSort;
import com.sparta.logistics.product.infrastructure.persistence.search.sort.Sort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public Page<ProductEntity> findAll(ProductSearchCondition searchCondition) {

        BooleanExpression searchExpression = getSearchCondition(searchCondition);

        List<ProductEntity> contents = queryFactory
            .selectFrom(productEntity)
            .where(searchExpression)
            .orderBy(getOrderSpecifiers(searchCondition.getSort()).toArray(new OrderSpecifier[0]))
            .offset(searchCondition.getPage())
            .limit(searchCondition.getSize())
            .fetch();

        long total = Optional.ofNullable(queryFactory.select(productEntity.count())
            .from(productEntity)
            .where(searchExpression)
            .fetchOne()).orElse((long) 0);

        PageRequest pageRequest = PageRequest.of(searchCondition.getPage(),
            searchCondition.getSize());
        return new PageImpl<>(contents, pageRequest, total);
    }

    private BooleanExpression getSearchCondition(ProductSearchCondition searchCondition) {
        return productEntity.isDeleted.isFalse()
            .and(isHubIdEqual(searchCondition.getHubId()))
            .and(isCompanyIdEqual(searchCondition.getCompanyId()))
            .and(containsKeyword(searchCondition.getKeyword()));
    }

    private BooleanExpression isHubIdEqual(Long hubId) {
        return  (hubId == null) ? null : productEntity.hubId.eq(hubId);
    }

    private BooleanExpression isCompanyIdEqual(Long companyId) {
        return  (companyId == null) ? null : productEntity.companyId.eq(companyId);
    }

    private BooleanExpression containsKeyword(String keyword) {
        return  (keyword == null) ? null : productEntity.name.containsIgnoreCase(keyword);
    }

    private List<OrderSpecifier<?>> getOrderSpecifiers(Sort sort) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        if (sort instanceof ProductSort productSort) {
            String sortType = productSort.getSortType();
            boolean isAsc = productSort.isAsc();

            if ("created_at".equalsIgnoreCase(sortType)) {
                orderSpecifiers.add(new OrderSpecifier<>(isAsc ? Order.ASC : Order.DESC, productEntity.id));
            } else if ("name".equalsIgnoreCase(sortType)) {
                orderSpecifiers.add(new OrderSpecifier<>(isAsc ? Order.ASC : Order.DESC, productEntity.name));
            }
        }
        return orderSpecifiers;
    }
}
