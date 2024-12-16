package com.sparta.logistics.delivery.infrastructure.persistence.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;


import com.sparta.logistics.delivery.domain.vo.DeliveryStatus;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.DeliveryEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.entity.QDeliveryEntity;
import com.sparta.logistics.delivery.infrastructure.persistence.search.DeliverySearchCondition;
import com.sparta.logistics.delivery.infrastructure.persistence.search.sort.DeliverySort;
import com.sparta.logistics.delivery.infrastructure.persistence.search.sort.Sort;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sparta.logistics.delivery.infrastructure.persistence.entity.QDeliveryEntity.deliveryEntity;

@RequiredArgsConstructor
@Component
public class DeliveryQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public Page<DeliveryEntity> findAll(DeliverySearchCondition searchCondition) {
        QDeliveryEntity deliveryEntity = QDeliveryEntity.deliveryEntity;

        BooleanExpression searchExpression = getSearchCondition(searchCondition);

        List<DeliveryEntity> contents = queryFactory
                .selectFrom(deliveryEntity)
                .where(searchExpression)
                .orderBy(getOrderSpecifiers(searchCondition.getSort()).toArray(new OrderSpecifier[0]))
                .offset(searchCondition.getPage())
                .limit(searchCondition.getSize())
                .fetch();

        long total = Optional.ofNullable(queryFactory.select(deliveryEntity.count())
                .from(deliveryEntity)
                .where(searchExpression)
                .fetchOne()).orElse(0L);

        PageRequest pageRequest = PageRequest.of(searchCondition.getPage(),
                searchCondition.getSize());
        return new PageImpl<>(contents, pageRequest, total);
    }

    private BooleanExpression getSearchCondition(DeliverySearchCondition searchCondition) {
        return deliveryEntity.isDeleted.isFalse()
                .and(hasDeliveryAddress(searchCondition.getDeliveryAddress()))
                .and(hasStatus(searchCondition.getStatus()))
                .and(hasRecipientName(searchCondition.getRecipientName()))
                .and(hasRecipientSnsId(searchCondition.getRecipientSnsId()))
                .and(hasOrderId(searchCondition.getOrderId()))
                .and(hasOriginHubId(searchCondition.getOriginHubId()))
                .and(hasDestinationHubId(searchCondition.getDestinationHubId()))
                .and(containsKeyword(searchCondition.getKeyword()));
    }


    private BooleanExpression hasStatus(String status) {
        if (status == null) {
            return null;
        }
        try {
            DeliveryStatus deliveryStatus = DeliveryStatus.valueOf(status.toUpperCase());
            return deliveryEntity.status.eq(deliveryStatus);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private BooleanExpression hasDeliveryAddress(String address) {
        return StringUtils.hasText(address) ? deliveryEntity.deliveryAddress.containsIgnoreCase(address) : null;
    }

    private BooleanExpression hasRecipientName(String name) {
        return StringUtils.hasText(name) ? deliveryEntity.recipientName.containsIgnoreCase(name) : null;
    }

    private BooleanExpression hasRecipientSnsId(String snsId) {
        return StringUtils.hasText(snsId) ? deliveryEntity.recipientSnsId.containsIgnoreCase(snsId) : null;
    }

    private BooleanExpression hasOrderId(Long orderId) {
        return orderId == null ? null : deliveryEntity.orderId.eq(orderId);
    }

    private BooleanExpression hasOriginHubId(Long hubId) {
        return hubId == null ? null : deliveryEntity.originHubId.eq(hubId);
    }

    private BooleanExpression hasDestinationHubId(Long hubId) {
        return hubId == null ? null : deliveryEntity.destinationHubId.eq(hubId);
    }

    private BooleanExpression containsKeyword(String keyword) {
        if (!StringUtils.hasText(keyword)) {
            return null;
        }
        return deliveryEntity.recipientName.containsIgnoreCase(keyword)
                .or(deliveryEntity.deliveryAddress.containsIgnoreCase(keyword))
                .or(deliveryEntity.recipientSnsId.containsIgnoreCase(keyword));
    }

    private List<OrderSpecifier<?>> getOrderSpecifiers(Sort sort) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        if (sort instanceof DeliverySort deliverySort) {
            String sortType = deliverySort.getSortType();
            boolean isAsc = deliverySort.isAsc();

            switch (sortType.toLowerCase()) {
                case "created_at" -> orderSpecifiers.add(new OrderSpecifier<>(
                        isAsc ? Order.ASC : Order.DESC, deliveryEntity.createdAt));
                case "status" -> orderSpecifiers.add(new OrderSpecifier<>(
                        isAsc ? Order.ASC : Order.DESC, deliveryEntity.status));
                case "recipient" -> orderSpecifiers.add(new OrderSpecifier<>(
                        isAsc ? Order.ASC : Order.DESC, deliveryEntity.recipientName));
                default -> orderSpecifiers.add(new OrderSpecifier<>(
                        Order.DESC, deliveryEntity.createdAt));
            }
        }
        return orderSpecifiers;
    }
}