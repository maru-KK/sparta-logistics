package com.sparta.logistics.order.infrastructure.persistence.adapter;

import static com.sparta.logistics.order.infrastructure.persistence.entity.QOrderEntity.*;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.order.infrastructure.persistence.entity.OrderEntity;
import com.sparta.logistics.order.infrastructure.persistence.entity.vo.OrderEntityStatus;
import com.sparta.logistics.order.infrastructure.persistence.search.OrderSearchCondition;
import com.sparta.logistics.order.infrastructure.persistence.search.sort.OrderSort;
import com.sparta.logistics.order.infrastructure.persistence.search.sort.Sort;
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
public class OrderPersistenceQueryAdapter {

    private final JPAQueryFactory queryFactory;

    public Page<OrderEntity> findAll(OrderSearchCondition searchCondition) {

        BooleanExpression searchExpression = getSearchCondition(searchCondition);

        List<OrderEntity> contents = queryFactory
            .selectFrom(orderEntity)
            .where(searchExpression)
            .orderBy(getOrderSpecifiers(searchCondition.getSort()).toArray(new OrderSpecifier[0]))
            .offset(searchCondition.getPage())
            .limit(searchCondition.getSize())
            .fetch();

        long total = Optional.ofNullable(queryFactory
            .select(orderEntity.count())
            .from(orderEntity)
            .where(searchExpression)
            .fetchOne()).orElse((long) 0);

        PageRequest pageRequest = PageRequest.of(searchCondition.getPage(),
            searchCondition.getSize());
        return new PageImpl<>(contents, pageRequest, total);
    }

    private BooleanExpression getSearchCondition(OrderSearchCondition searchCondition) {
        return orderEntity.isDeleted.isFalse()
            .and(isSupplyCompanyIdEqual(searchCondition.getSupplyCompanyId()))
            .and(isProductIdEqual(searchCondition.getProductId()))
            .and(isStatusEqual(searchCondition.getStatus()));
    }

    private BooleanExpression isSupplyCompanyIdEqual(Long supplyCompanyId) {
        return  (supplyCompanyId == null) ? null : orderEntity.supplyCompanyId.eq(supplyCompanyId);
    }

    private BooleanExpression isProductIdEqual(Long productId) {
        return  (productId == null) ? null : orderEntity.productId.eq(productId);
    }

    private BooleanExpression isStatusEqual(OrderEntityStatus status) {
        return (status == null) ? null : orderEntity.status.eq(status);
    }

    private List<OrderSpecifier<?>> getOrderSpecifiers(Sort sort) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        if (sort instanceof OrderSort productSort) {
            String sortType = productSort.getSortType();
            boolean isAsc = productSort.isAsc();

            if ("created_at".equalsIgnoreCase(sortType)) {
                orderSpecifiers.add(new OrderSpecifier<>(isAsc ? Order.ASC : Order.DESC, orderEntity.orderId));
            } else if ("name".equalsIgnoreCase(sortType)) {
                orderSpecifiers.add(new OrderSpecifier<>(isAsc ? Order.ASC : Order.DESC, productEntity.name));
            }
        }
        return orderSpecifiers;
    }
}
