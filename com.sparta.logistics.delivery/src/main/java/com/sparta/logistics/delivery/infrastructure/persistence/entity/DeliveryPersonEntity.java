package com.sparta.logistics.delivery.infrastructure.persistence.entity;

import com.sparta.logistics.delivery.domain.DeliveryPerson;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonStatus;
import com.sparta.logistics.delivery.domain.vo.DeliveryPersonType;
import com.sparta.logistics.delivery.infrastructure.external.auth.dto.UserDetailResponse;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.function.Supplier;

import static com.sparta.logistics.delivery.domain.vo.DeliveryPersonStatus.AVAILABLE;

@Entity(name = "p_delivery_person")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class DeliveryPersonEntity extends BaseEntity {
    @Id
    @Column(name = "delivery_person_id")
    private Long deliveryPersonId;

    @Column(nullable = false)
    private String snsId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryPersonType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryPersonStatus status;

    @Column(nullable = false)
    private Integer sequence;

    public void setStatus(DeliveryPersonStatus status) {
        this.status = status;
    }

    public static DeliveryPersonEntity of(DeliveryPerson request, UserDetailResponse user, int sequence) {
        return DeliveryPersonEntity.builder()
                .deliveryPersonId(user.userId())
                .snsId(user.snsAccount())
                .type(request.type())
                .status(request.status())
                .sequence(sequence)
                .build();
    }

    public void update(DeliveryPerson requestDto, Supplier<Integer> maxSequenceSupplier) {
        if (requestDto.status().equals(AVAILABLE) && !this.status.equals(AVAILABLE)) {
            this.sequence = maxSequenceSupplier.get() + 1;
        }
        this.snsId = requestDto.snsId();
        this.type = requestDto.type();
        this.status =requestDto.status();
    }
}
