package com.sparta.logistics.infra.infrastructure.persistence.entity;

import com.sparta.logistics.infra.domain.slack.Slack;
import com.sparta.logistics.infra.domain.slack.SlackToSendMessage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_sns")
@Entity(name = "sns")
public class SnsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipientSnsId;
    private String message;
    private LocalDateTime sentAt;

    public static List<SnsEntity> from(SlackToSendMessage slack) {
        LocalDateTime now = LocalDateTime.now();

        List<SnsEntity> snsEntities = new ArrayList<>();
        for (String recipientId : slack.getRecipientId()) {
            snsEntities.add(new SnsEntity(
                null, recipientId, slack.getMessage(), now
            ));
        }
        return snsEntities;
    }

    public Slack toDomain() {
        return new Slack(id, recipientSnsId, message, sentAt);
    }
}
