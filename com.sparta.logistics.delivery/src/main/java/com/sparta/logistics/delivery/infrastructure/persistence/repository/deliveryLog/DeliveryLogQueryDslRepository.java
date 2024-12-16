package com.sparta.logistics.delivery.infrastructure.persistence.repository.deliveryLog;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.logistics.delivery.domain.vo.DeliveryLogStatus;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryLogEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.QDeliveryLogEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.search.DeliveryLogSearchCondition;
import com.sparta.logistics.delivery.infrastructure.persistence.search.sort.DeliverySort;
import com.sparta.logistics.delivery.infrastructure.persistence.search.sort.Sort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sparta.logistics.delivery.infrastructure.persistence.entity.QDeliveryLogEntity.deliveryLogEntity;

@RequiredArgsConstructor
@Component
public class DeliveryLogQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public Page<DeliveryLogEntity> findAll(DeliveryLogSearchCondition searchCondition) {
        QDeliveryLogEntity deliveryLog = deliveryLogEntity;

        BooleanExpression searchExpression = getSearchCondition(searchCondition);

        List<DeliveryLogEntity> contents = queryFactory
                .selectFrom(deliveryLog)
                .where(searchExpression)
                .orderBy(getOrderSpecifiers(searchCondition.getSort()).toArray(new OrderSpecifier[0]))
                .offset(searchCondition.getPage())
                .limit(searchCondition.getSize())
                .fetch();

        long total = Optional.ofNullable(queryFactory.select(deliveryLog.count())
                .from(deliveryLog)
                .where(searchExpression)
                .fetchOne()).orElse(0L);

        PageRequest pageRequest = PageRequest.of(searchCondition.getPage(),
                searchCondition.getSize());
        return new PageImpl<>(contents, pageRequest, total);
    }

    private BooleanExpression getSearchCondition(DeliveryLogSearchCondition searchCondition) {
        return deliveryLogEntity.isDeleted.isFalse()
                .and(hasDeliveryId(searchCondition.getDeliveryId()))
                .and(hasStatus(searchCondition.getStatus()))
                .and(hasOriginHubId(searchCondition.getOriginHubId()))
                .and(hasDestinationHubId(searchCondition.getDestinationHubId()))
                .and(hasDeliveryPersonId(searchCondition.getDeliveryPersonId()));
    }

    private BooleanExpression hasDeliveryId(Long deliveryId) {
        return deliveryId == null ? null : deliveryLogEntity.deliveryId.eq(deliveryId);
    }

    private BooleanExpression hasStatus(DeliveryLogStatus status) {
        return status == null ? null : deliveryLogEntity.status.eq(status);
    }

    private BooleanExpression hasOriginHubId(Long hubId) {
        return hubId == null ? null : deliveryLogEntity.originHubId.eq(hubId);
    }

    private BooleanExpression hasDestinationHubId(Long hubId) {
        return hubId == null ? null : deliveryLogEntity.destinationHubId.eq(hubId);
    }

    private BooleanExpression hasDeliveryPersonId(Long deliveryPersonId) {
        return deliveryPersonId == null ? null : deliveryLogEntity.deliveryPersonId.eq(deliveryPersonId);
    }

    private List<OrderSpecifier<?>> getOrderSpecifiers(Sort sort) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        if (sort instanceof DeliverySort deliverySort) {
            String sortType = deliverySort.getSortType();
            boolean isAsc = deliverySort.isAsc();

            switch (sortType.toLowerCase()) {
                case "created_at" -> orderSpecifiers.add(new OrderSpecifier<>(
                        isAsc ? Order.ASC : Order.DESC, deliveryLogEntity.createdAt));
                case "status" -> orderSpecifiers.add(new OrderSpecifier<>(
                        isAsc ? Order.ASC : Order.DESC, deliveryLogEntity.status));
                default -> orderSpecifiers.add(new OrderSpecifier<>(
                        Order.DESC, deliveryLogEntity.createdAt));
            }
        }
        return orderSpecifiers;
    }

}