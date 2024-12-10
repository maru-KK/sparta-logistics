package com.sparta.logistics.delivery.infrastructure.persistence.entity;

import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "p_delivery_person")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class DeliveryPersonEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_person_id")
    private Long deliveryPersonId;

    @Column(unique = true, nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long hubId;

    @Column(nullable = false)
    private String snsId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryPersonType type;

    @Column(nullable = false)
    private Integer sequence;

    public static DeliveryPersonEntity from(DeliveryPerson request) {
        return DeliveryPersonEntity.builder()
                .userId(request.userId())
                .hubId(request.hubId())
                .snsId(request.snsId())
                .type(request.type())
                .sequence(1)
                .build();
    }
}
